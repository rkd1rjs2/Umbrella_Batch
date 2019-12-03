package com.rainbow.um.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rainbow.um.dto.SmsData;


@Repository
public class BookSMSDaoImpl implements IBookSMSDao{

	private String NS="com.rainbow.um.model.IBookSMSDao."; 
	
	@Autowired
	SqlSessionTemplate service;

	@Override
	public List<SmsData> getSmsData() {
		return null;
	}
	
	

}
