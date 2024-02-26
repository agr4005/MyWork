package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.MemberDTO;
import pageTest.SearchCriteria;

public interface MemberService {

	// ** Member Check List
	public List<MemberDTO> mCheckList(SearchCriteria cri);
	public int mCheckRowsCount(SearchCriteria cri);

	//** Member_Paging
	public List<MemberDTO> mPageList(SearchCriteria cri);
	public int totalRowsCount(SearchCriteria cri);
	
	// ** selectList
	List<MemberDTO> selectList();

	// ** selectOne
	MemberDTO selectOne(String id);

	// ** insert
	int insert(MemberDTO dto);

	// ** update
	int update(MemberDTO dto);

	// ** delete
	int delete(String id);
	
	// ** selectJoList
	List<MemberDTO> selectJoList(int jno);

	// ** pwUpdate
	int pwUpdate(MemberDTO dto);
}