package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.JoDTO;

public interface JoService {

	// ** selectList
	List<JoDTO> selectList();

	// ** selectOne
	JoDTO selectOne(int jno);

	// ** insert
	int insert(JoDTO dto);

	// ** update
	int update(JoDTO dto);

	// ** delete
	int delete(int jno);

}