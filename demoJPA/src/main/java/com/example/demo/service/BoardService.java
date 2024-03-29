package com.example.demo.service;

import java.util.List;


import com.example.demo.domain.BoardDTO;
import pageTest.SearchCriteria;

public interface BoardService {

	// ** Ajax: idbList(id별 boardList)
	public List<BoardDTO> idbList(String id);	
	
	// ** Board Check List
	public List<BoardDTO> bCheckList(SearchCriteria cri);
	public int bCheckRowsCount(SearchCriteria cri);
	
	//** Board_Paging
	//=> ver01 : Criteria 사용
	//=> ver02 : SearchCriteria 사용
	public List<BoardDTO> bPageList(SearchCriteria cri);
	public int totalRowsCount(SearchCriteria cri);
	
	//	** selectList
	public List<BoardDTO> selectList();
	
	//	** selectOne
	public BoardDTO selectOne(int seq);
	
	//	** Insert
	public int insert(BoardDTO dto);
	
	//	** Update
	public int update(BoardDTO dto);
	
	//	** delete
	public int delete(BoardDTO dto);
	
	//	** replyInsert
	public int rinsert(BoardDTO dto);
	
}	//class
