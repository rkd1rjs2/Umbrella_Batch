package com.rainbow.um.common;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.DataOutputStream;

/**
 * 
 * @author RainbowStudy
 * @version 1.0.0
 * https://solapi.com의 시스템을 기반으로 한 단문 SMS 전송 모듈
 *
 * 사용법
 * 싱글턴 패턴이 입혀진 클래스로서 getInstance() 메소드로 클래스를 인스턴스화 하여 사용한다.
 * 인스턴스화 시 자동으로 앱버전과 타입의 값이 선언되며 문자의 형태는 SMS로 고정된다.
 * setAuth(HashMap<String, String> auth) 메소드를 사용하여 기본키, 비밀키, 보내는사람의 번호를 선언한다.
 * send(HashMap<String, String> message) 메소드를 사용하여 받는사람의 번호, 문자 내용을 파라미터로 보내면 문자 전송이 가능하다.
 * userAuthSend(HashMap<String, String> message) 메소드를 사용하여 받는사람의 번호, 문자 내용을 파라미터로 입력하며 내용에 {}을 포함한 부분은 6자리의 랜덤 숫자로 변경하여 문자를 전송한다.
 * 보내는 번호를 바꾸고 싶다면 setFrom(String from) 메소드를 사용한다
 * 
 * 회원가입, 기본키, 비밀키, 금액에 관한 안내는 https://solapi.com/ 를 참조한다.
 * 
 */

public class SMSauth {

	private String targetUrl;
	private String api_key;
	private String api_secret;
	private String from;
	
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	public void setApi_secret(String api_secret) {
		this.api_secret = api_secret;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	/**
	* 문자를 보내기 전 text내용에 인증번호를 추가하는 메소드
	* @param HashMap<String, String> message
	* to, text를 키로 받아서 사용하며 모두 필수로 입력되어야한다.
	* text에 입력된 '{}'를 '[숫자인증키6자리]' 로 바꾸어준다. 
	* text는 60자 제한이며, to는 '-'을 제외한 번호만 입력한다
	* @return Map<String, Object>
	* 문자 전송 완료 여부를 map의 키값 "code"로 반환한다
	* 난수로 발생된 6자리의 인증키를 map의 키값 "ket"로 반환한다.
	 * @throws Exception 
	*/ 
	public Map<String, Object> userAuthSend(HashMap<String, String> message) throws Exception {
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 6; i++) {
			temp.append((rnd.nextInt(10)));
		}
		
		String text = message.get("text");
		text = text.replace("{}", "["+String.valueOf(temp)+"]");
		
		message.put("text", text); 
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("code", send(message));
		map.put("key", String.valueOf(temp));
		
		return map;
	}
	
	/**
	 * @param HashMap<Stirng, String> message
	 * to, text
	 * @return int
	 * 문자 전송 여부를 코드로 반환한다 (200 = 성공)
	 * @throws Exception
	 */
	public int send(HashMap<String, String> message) throws Exception {
		System.out.println(message);
		System.out.println();
		System.out.println();
		URL url = new URL(targetUrl);
	    
	    String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(new Date());
	    
	    HttpURLConnection con = (HttpURLConnection) url.openConnection();
	    con.setRequestMethod("POST");
	    
	    String salt = setSalt();
	    
	    HashMap<String, String> signature = new HashMap<String, String>();
	    signature.put("data", date+salt);
	    signature.put("key", api_secret);
	    
	    String parameters = "{\"message\":{\"to\":\""+setNumber(message.get("to"))+"\",\"from\":\""+setNumber(from)+"\",\"text\":\""+message.get("text")+"\"}}";
	    String auth = "HMAC-SHA256 apiKey="+api_key+", date="+date+", salt="+salt+", signature="+hmac(signature);
	    
	    con.setRequestProperty("Authorization", auth);
	    con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
	    con.setDoOutput(true);
	    
	    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	    
	    wr.write(parameters.getBytes("UTF-8"));

	    wr.flush();
	    wr.close();

	    int responseCode = con.getResponseCode();
	    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
	    
	    String line;
	    StringBuffer response = new StringBuffer();
	    while ((line = in.readLine()) != null) {
	      response.append(line);
	    }
	    in.close();

	    System.out.println("HTTP response code : " + responseCode);
	    System.out.println("HTTP body : " + response.toString());
	    
	    return responseCode;
	}
	
	/**
	 * HMAC인증을 통해 signature코드를 반한하기 위한 위한 메소드
	 * @param HashMap<String, String> key
	 * data와 key를 키로 받아서 사용하며 모두 필수로 입력되어야한다.
	 * @return String result 생성된 signature 키를 반환한다
	 */
	public static String hmac(HashMap<String, String> key) throws NoSuchAlgorithmException, InvalidKeyException {
		final Charset asciiCs = Charset.forName("US-ASCII");
        final Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        final SecretKeySpec secret_key = new javax.crypto.spec.SecretKeySpec(asciiCs.encode(key.get("key")).array(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        final byte[] mac_data = sha256_HMAC.doFinal(asciiCs.encode(key.get("data")).array());
        String result = "";
        for (final byte element : mac_data){
           result += Integer.toString((element & 0xff) + 0x100, 16).substring(1);
        }
        return result;
	}
	
	/**
	 * 20자리의 불규칙한 문자열을 생성하는 메소드
	 * @return String
	 */
	private String setSalt() {
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 20; i++) {
		    int rIndex = rnd.nextInt(3);
		    switch (rIndex) {
		    case 0:
		        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
		        break;
		    case 1:
		        temp.append((char) ((int) (rnd.nextInt(26)) + 65));
		        break;
		    case 2:
		        temp.append((rnd.nextInt(10)));
		        break;
		    }
		}
		return String.valueOf(temp);
	}
	
	/**
	 * 전화번호에 -가 포함된다면 제거해주는 메소드
	 * @param String number
	 * @return String
	 */
	private String setNumber(String number) {
		return number.replace("-", "");
	}
}
