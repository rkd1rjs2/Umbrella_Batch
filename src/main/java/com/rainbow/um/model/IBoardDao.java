package com.rainbow.um.model;

import java.util.List;

import com.rainbow.um.dto.BoardDto;
import com.rainbow.um.dto.BobDto;

public interface IBoardDao {

	public boolean bobInsert(BoardDto dto);
	public List<BobDto> bobLoanList();
	
	
	
}
