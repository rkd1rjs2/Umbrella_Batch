package com.rainbow.um.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rainbow.um.dto.SmsData;



@Service
public class BookSMSServiceImpl  implements IBookSMSService{

	@Autowired
	IBookSMSDao dao;

	@Override
	public List<SmsData> getSmsData() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
}
