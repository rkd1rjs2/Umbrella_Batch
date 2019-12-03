package com.rainbow.um.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class BookDaoImpl implements IBookDao{

	private String NS="com.rainbow.um.model.IBookDao."; 
	
	@Autowired
	SqlSessionTemplate service;
	
	

}
