package com.rainbow.um.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rainbow.um.dto.SmsData;
import com.rainbow.um.dto.UserForSms;


@Repository
public class BookSMSDaoImpl implements IBookSMSDao{

	private String NS="com.rainbow.um.model.IBookSMSDao."; 
	
	@Autowired
	SqlSessionTemplate sqlsessiontemplate;

	@Override
	public List<SmsData> getSmsData() {
		return sqlsessiontemplate.selectList(NS+"getSmsData");
	}

	@Override
	public List<String> selectOverResv() {
		return sqlsessiontemplate.selectList(NS+"selectOverResv");
	}

	@Override
	public Integer updateOverResv() {
		return sqlsessiontemplate.update(NS+"updateOverResv");
	}

	@Override
	public UserForSms chkUser(String book_cseq) {
		return sqlsessiontemplate.selectOne(NS+"chkUser", book_cseq);
	}

	@Override
	public Integer updateStep(String book_cseq) {
		return sqlsessiontemplate.update(NS+"updateStep", book_cseq);
	}
	
	

}
