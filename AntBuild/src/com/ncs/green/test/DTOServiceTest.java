package com.ncs.green.test;

import com.ncs.green.domain.UserDTO;
import com.ncs.green.service.DTOservice;

public class DTOServiceTest {

	public static void main(String[] args) {
		//	1) UserDTO 생성
		UserDTO dto = new UserDTO();
		dto.setId("banana");
		dto.setName("홍길동");
		dto.setLoginTime("2023/02/22 AM 10:04:04");
		
		//	2) 직접 출력
		System.out.println("** 직접 출력 => " +dto);
		
		//	3) DTOService로 출력
		DTOservice service = new DTOservice();
		service.setUserDTO(dto);
		System.out.println("** AntBuild Test 출력 **");
		System.out.println("** DTOService =>  " + service.getUserDTO());
		
	} //main

} //class
