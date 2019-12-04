package com.rainbow.um.common;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.rainbow.um.dto.SmsData;
import com.rainbow.um.model.IBookSMSService;


public class ExecuteUsingQuartz {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private IBookSMSService service;
	
	
	public void sendMessage() throws Exception {
		List<SmsData> liste= service.getSmsData();
		for (int i = 0; i < liste.size(); i++) {
			System.out.println(liste.get(i));
		}
	}
	
	public void resvControll() {
		
	}
	
	public void bobListInsert() {
		
	}
	
	
}
