package com.rainbow.um.common;

import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class OtpWAS {
	private static String ALGORITHM;
	private static byte[] SECRET_KEY;
	
	public OtpWAS() {
	}
	
	public OtpWAS(String ALGORITHM, String SECRET_KEY) {
		this.ALGORITHM = ALGORITHM;
		this.SECRET_KEY = SECRET_KEY.getBytes();
	}
	
	private long create(long time, String phone) throws Exception {
		byte[] data = new byte[8];
		
		long value = time;
		for (int i = 8; i-- > 0; value >>>= 8) {
			data[i] = (byte) value;
		}
		
		byte[] phoneByte = phone.getBytes();
		byte[] newSecret = new byte[phoneByte.length+ SECRET_KEY.length];
		System.arraycopy(phoneByte, 0, newSecret, 0, phoneByte.length);
		System.arraycopy(SECRET_KEY, 0, newSecret, phoneByte.length, SECRET_KEY.length);
		
		Mac mac = Mac.getInstance(ALGORITHM);
		mac.init(new SecretKeySpec(newSecret, ALGORITHM));
	 
		byte[] hash = mac.doFinal(data);
		int offset = hash[20 - 1] & 0xF;
	 
		long truncatedHash = 0;
		for (int i = 0; i < 4; ++i) {
			truncatedHash <<= 8;
			truncatedHash |= hash[offset + i] & 0xFF;
		}
	 
		truncatedHash &= 0x7FFFFFFF;
		truncatedHash %= 1000000;
	 
		return truncatedHash;
	}
	
	private String create(String phone, long date) throws Exception {
		return String.format("%06d", create(date, phone));
	}
	
	public boolean vaildate(String key, String phone, long date) throws Exception {
		if(new Date().getTime()-30000> date) {
			return false;
		}
		
		return create(phone, date).equals(key);
	}
}
