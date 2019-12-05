package com.rainbow.um.model;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rainbow.um.dto.BoardDto;
import com.rainbow.um.dto.BobDto;
/**
 * 게시판 기능을 제어하는 class
 * @author kim93
 *
 */
@Service
public class BoardServiceImpl implements IBoardService {

	private final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired
	private IBoardDao dao;
	

	/**
	 * <h2>추천도서 등록</h2>
	 * @since 19.11.26
	 * @param 글의 제목 내용
	 * @return 성공여부 성공true &#47; 실패 false
	 */
	@Override
	public boolean bobInsert(BoardDto dto) {
		log.info("bobInsert 추천도서 등록 : {}",dto);
		List<BobDto> lists = dao.bobLoanList();
		StringBuffer content = new StringBuffer();
		for (BobDto bobDto : lists) {
			content.append("책 제목: ").append(bobDto.getBook_name()).append("<br>");
			content.append("대여 권수: ").append(bobDto.getCu()).append("<br><hr>");
		}
		System.out.println(content.toString());
		dto.setBoard_content(content.toString());
		System.out.println(dto);
		return dao.bobInsert(dto);
	}


}
