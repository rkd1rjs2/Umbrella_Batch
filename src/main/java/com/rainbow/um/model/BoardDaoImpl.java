package com.rainbow.um.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rainbow.um.dto.BoardDto;
import com.rainbow.um.dto.BobDto;

@Repository
public class BoardDaoImpl implements IBoardDao {

	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "com.rainbow.um.model.IBoardDao.";
	

	@Override
	public boolean bobInsert(BoardDto dto) {
		int n = session.insert(NS+"bobInsert",dto);
		return n>0?true:false;
	}

	@Override
	public List<BobDto> bobLoanList() {
		return session.selectList(NS+"bobLoanList");
	}


}
