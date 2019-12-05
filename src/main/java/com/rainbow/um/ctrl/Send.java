package com.rainbow.um.ctrl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rainbow.um.common.OtpWAS;
import com.rainbow.um.common.SMSauth;

@Controller
public class Send {

	@Autowired
	private OtpWAS otp;
	@Autowired
	private SMSauth sms;
	
	@RequestMapping(value = "/send.do", method = RequestMethod.GET)
	@ResponseBody
	public String send(HttpServletRequest request) throws Exception {
//		String ip = request.getRemoteAddr();
//		if(ip.equalsIgnoreCase("192.168.1.16")) {
			String to = (String)request.getParameter("phone");
			long time = Long.parseLong(request.getParameter("time"));
			
			String auth = otp.create(to, time);
			String text = to+"님의 인증 번호는 [" + auth +"] 입니다.";
			
			HashMap<String, String> message = new HashMap<String, String>();
			message.put("to", to);
			message.put("text", text);
			System.out.println(message);
			System.out.println(auth);
//			if(sms.send(message) == 200) {
				return "success";
//			}else {
//				return "false";
//			}
//		}else {
//			System.out.println("잘못된 사용자");
//			return "false";
//		}
	}
	
}
