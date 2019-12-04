package com.rainbow.um.common;

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
	private IBoardService boardService;
	
	
	public void sendMessage() throws Exception {
		List<SmsData> liste= service.getSmsData();
		for (int i = 0; i < liste.size(); i++) {
			System.out.println(liste.get(i));
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
