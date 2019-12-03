package com.rainbow.um.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class BookServiceImpl  implements IBookService{

	@Autowired
	IBookDao dao;

	
	
	
	
}
