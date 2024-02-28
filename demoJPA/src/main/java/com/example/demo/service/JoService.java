package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.JoDTO;



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