package com.rainbow.um.common;

import java.util.HashMap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.rainbow.um.dto.BoardDto;
import com.rainbow.um.dto.SmsData;
import com.rainbow.um.model.IBoardService;
import com.rainbow.um.model.IBookSMSService;


public class ExecuteUsingQuartz {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private IBookSMSService service;
	
	@Autowired
	private SMSauth sms;
	
	@Autowired
	private IBoardService boardService;
	
	
	public void sendMessage() throws Exception {
		List<SmsData> lists= service.getSmsData();
		String to = "phone";
		String text = "내용";
		
		HashMap<String, String> message = new HashMap<String, String>();
			for (int i = 0; i < lists.size(); i++) {
				to=lists.get(i).getUser_phone();
				text="[반납알림문자] 대출하신 "+lists.get(i).getBook_name()+"의 반납일이 "+lists.get(i).getMdate()+"일 남았습니다"
						+ "연체에대한 불이익이 발생할수있으니 반납일 전까지 반납 부탁드립니다.";
				message.put("to", to);
				message.put("text", text);
				sms.send(message);
		}
		
		
	}
	
	public void resvControll() {
		
	}
	
	public void bobListInsert() {
		BoardDto dto = new BoardDto();
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH,-1);
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMM");
		String beforeMonth = dateFormat.format(cal.getTime()).substring(4,6);
		String beforeYear = dateFormat.format(cal.getTime()).substring(0,4);
		dto.setBoard_title(beforeYear+"년 "+beforeMonth+"월 추천 도서!");
		boardService.bobInsert(dto);
		
	}
	
	
}
