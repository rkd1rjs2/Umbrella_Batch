package com.rainbow.um.model;

import java.util.List;

import com.rainbow.um.dto.SmsData;
import com.rainbow.um.dto.UserForSms;

public interface IBookSMSDao {

	public List<SmsData>getSmsData();
	public List<String> selectOverResv();
	public Integer updateOverResv();
	public UserForSms chkUser(String book_cseq);
	public Integer updateStep(String book_cseq);
	
}
