package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;

public interface MemberService {

	//	1) JPARepository Method 규약
	//	=> Jno별 Member 출력
	List<Member> findByJno(int jno);
	
	// ** selectList
	List<Member> selectList();

	// ** selectOne
	Member selectOne(String id);

	// ** insert, update
	Member save(Member entity);
	
	// ** Password Update
	//	=> @Query 적용
	void updatePassword(String id,String password); 

	// ** delete
	void deleteById(String id);

	   // ** Join
	   List<MemberDTO> findMemberJoin();
	   
	   List<MemberDTO> findMemberJoin2();
	   
}