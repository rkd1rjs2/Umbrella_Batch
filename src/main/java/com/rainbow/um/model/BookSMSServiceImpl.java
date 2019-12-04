package com.rainbow.um.model;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rainbow.um.common.SMSauth;
import com.rainbow.um.dto.SmsData;
import com.rainbow.um.dto.UserForSms;



@Service
public class BookSMSServiceImpl  implements IBookSMSService{

	@Autowired
	private IBookSMSDao dao;
	
	@Autowired
	private SMSauth sms;

	@Override
	public List<SmsData> getSmsData() {
		return dao.getSmsData();
	}

	@Override
	public void sendResvSms() {
		// 연체된 도서들의 목록 확인
		List<String> bookList = dao.selectOverResv();
		dao.updateOverResv();
		
		if(bookList.size()>0?true:false) {
			for(String book_cseq : bookList) {
				UserForSms forSms = dao.chkUser(book_cseq);
				if(forSms != null) {
					HashMap<String, String> message = new HashMap<String, String>();
					String bookName = forSms.getBook_name();
					if(bookName.length()>8) {
						bookName = bookName.substring(0, 6) + "..";
					}
					message.put("to", forSms.getUser_phone());
					message.put("text", "회원님께서 예약하신 도서 " + bookName + "가 현재 대출 가능한 상태입니다.");
					try {
						//sms.send(message);
					} catch (Exception e) {
						System.out.println("메세지 전송 에러");
					}
					dao.updateStep(book_cseq);
				}
			}
		}
		
	}

	
	
	
	
}
