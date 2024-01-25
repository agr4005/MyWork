package com.ncs.spring01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.MemberService;

@Controller
public class MemberController {
	
	@Autowired(required = false)
	MemberService service;
	
	// ** Member List
	@RequestMapping(value = {"/mlistsp","/mlist"}, method = RequestMethod.GET)
	public String mList(Model model) {

		model.addAttribute("banana", service.selectList());
		return "member/memberList";
	}	//mList
	
	//	** Member Detail 
	@RequestMapping(value = {"/mdetailsp","/mdetail"}, method = RequestMethod.GET)
	public String mDetail(Model model) {
		
		model.addAttribute("apple", service.selectOne("agr4005"));
		return "member/memberDetail";
	}	//mDetail
	
	
}	//class


