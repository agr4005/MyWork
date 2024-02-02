package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.MemberDAO;

@Service
@Repository
public class MemberServiceImpl implements MemberService {
	// ** 전역변수 정의
	@Autowired
	MemberDAO dao;
//	MemberDAO dao = new MemberDAO();
	
	// ** selectList
		@Override
		public List<MemberDTO> selectList() {
			return dao.selectList();
		}
	
	// ** selectOne
		@Override
		public MemberDTO selectOne(String id) {
			return dao.selectOne(id);
		}
		
	// ** insert
		@Override
		public int insert(MemberDTO dto) {
			return dao.insert(dto);
		}
		
	// ** update
		@Override
		public int update(MemberDTO dto) {
			return dao.update(dto);
		}
		
	// ** delete
		@Override
		public int delete(String id) {
			return dao.delete(id);
		}
		
	// ** selectJoList
		@Override
		public List<MemberDTO> selectJoList(int jno) {
			return dao.selectJoList(jno);
		}
		
	// ** pwUpdate
		@Override
		public int pwUpdate(MemberDTO dto) {
			return dao.pwUpdate(dto);
		}
		

}
