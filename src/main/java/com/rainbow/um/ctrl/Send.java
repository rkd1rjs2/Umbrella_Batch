package com.rainbow.um.ctrl;

import java.util.Date;
import java.util.HashMap;

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
	
	@ResponseBody
	@RequestMapping(value = "/send.do", method = RequestMethod.GET)
	public String send(HttpServletRequest request) throws Exception {
		String to = (String)request.getParameter("phone");
		
		String auth = otp.create(to, new Date().getTime());
		String text = to+"님의 인증 번호는 [" + auth +"] 입니다.";

		HashMap<String, String> message = new HashMap<String, String>();
		message.put("to", to);
		message.put("text", text);
		
		System.out.println(message.toString());
		System.out.println(new Date().getTime());
		System.out.println(new Date());
		
		return "";
		
//		if(sms.send(message) == 200) {
//			return "success";
//		}else {
//			return "false";
//		}
	}
	
}
