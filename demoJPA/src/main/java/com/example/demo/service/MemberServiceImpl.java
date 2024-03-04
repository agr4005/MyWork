package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberDSLRepositoryImpl;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MyRepository;

import lombok.RequiredArgsConstructor;

//	** Interface 자동완성
//	** Alt + Shift + T
//	=> 또는 마우스 우클릭 PopUp Menu와 Refactor -Extract Interface..

//** Mybatis 적용
//=> CRUD 처리를 Mapper 를 이용
//=> DAO 대신 Mapper interface ->  ~Mapper.xml

//** Mybatis interface 방식으로 적용
//=> MemberDAO 대신 MemberMapper 사용
//=> MemberMapper 의 인스턴스를 스프링이 생성해주고 이를 주입받아 실행함
//(스프링이 생성해주는 동일한 타입의 클래스는 JUnit Test 로 확인가능, 추후 실습) 
//=> 단, 설정화일에 <mybatis-spring:scan base-package="mapperInterface"/> 반드시 추가해야함
//  MemberDAO의 Sql구문 처리를 mapperInterface 사용으로 MemberMapper 가 역할을 대신함

//=> SQL 구문 : xml 로작성 -> 이 화일을 Mapper 라 함 
//=> Mapper 작성규칙
// -> mapperInterface 와 패키지명, 화일명이 동일해야함
// -> 즉, Java interface, Mapper, Mapper.xml 의 namespace의 값(패키지와 파일명)이 모두 동일해야함
//	-> 그리고 해당 메서드는 Mapper의 xml 구문의 id 속성값으로 찾음.

@Service
@RequiredArgsConstructor
	public class MemberServiceImpl implements MemberService { 
	
	private final MemberRepository repository;
	private final MyRepository emrepository;
	private final MemberDSLRepositoryImpl dslrepository;

	@Override
	public List<Member> findByJno(int jno) {
		//return repository.findByJno(jno); // ver01
		return dslrepository.findMemberJnoDSL(jno);
	}
	
	// ** selectList

	@Override
	public List<Member> selectList() {
		//return repository.findAll(); //ver01
		return emrepository.emMemberList();	//ver02: EntityManager Test
	}

	// ** selectOne

	@Override
	public Member selectOne(String id) {
		
//		Optional<Member> result = repository.findById(id);
//		if(result.isPresent()) return result.get();
//		else return null;	//ver01
		
		return emrepository.emMemberDetail(id);
		//	ver02: EntityManager Test
	}

	// ** insert, update

	@Override
	public Member save(Member entity) {
		return repository.save(entity);
	}

	// ** delete

	@Override
	public void deleteById(String id) {
		 repository.deleteById(id);
	}
	
	// ** pwUpdate
	
	@Override
	public void updatePassword(String id, String password) {
		repository.updatePassword(id, password);
	}
	
	   // ** Join
	   @Override
	   public List<MemberDTO> findMemberJoin() {
<<<<<<< HEAD
	      return repository.findMemberJoin();
=======
	      return dslrepository.findMemberJoinDSL();
	   }
	   
	   // ** Join
	   @Override
	   public List<MemberDTO> findMemberJoin2() {
		   return repository.findMemberJoin2();
>>>>>>> branch 'master' of https://github.com/agr4005/MyWork.git
	   }

}
