package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.service.JoService;

@Controller
@RequestMapping(value="/jo")
public class JoController {
	
	@Autowired(required = false)
	JoService service;

	@RequestMapping(value = "/joList", method = RequestMethod.GET)
	public void mList(Model model) {
		model.addAttribute("banana", service.selectList());
	}	//joList
	
	
	@RequestMapping(value = "/joDetail", method = RequestMethod.GET)
	public String detail(Model model, @RequestParam("jno") int jno, JoDTO dto) {

		int id = dto.getJno();
		String uri = "jo/joDetail";	
		
		model.addAttribute("apple", service.selectOne(id));
		return uri;	
	}	//joDetail
	
	@RequestMapping(value = "/joinForm", method = RequestMethod.GET)
	public void joinForm() {
	}	
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Model model, JoDTO dto) {

		String uri = "redirect:/jo/joList";	// 성공시

		//	2. Service & 결과처리
		if (service.insert(dto) > 0) {
			// 성공시
			model.addAttribute("message","~~ 조 등록 성공! ~~");
		} else {
			// 실패시
			uri = "jo/joinForm";		
			model.addAttribute("message","~~ 조 등록 실패! ~~");
		}
		return uri;	
	}	//join
	
	@RequestMapping(value = "/joUpdate", method = RequestMethod.GET)
	public void joUpdate(@RequestParam("jno") int jno, Model model) {
		model.addAttribute( "apple", service.selectOne(jno));
	}	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(RedirectAttributes rttr, Model model, JoDTO dto) {
		
		String uri = "redirect:/jo/joList";	// 성공시
		
		model.addAttribute("apple" , dto);
		
		//	2. Service & 결과처리
		if (service.update(dto) > 0) {
			rttr.addFlashAttribute("message","~~ 조 정보 수정 성공!! ~~");
		} else {
			// 실패 : 재수정 유도
			uri = "jo/joUpdate";		
			rttr.addFlashAttribute("message","~~ 조 정보 수정 오류!! 다시 하세요 ~~");
		}
		return uri;	
	}	//update
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Model model, RedirectAttributes rttr, JoDTO dto) {
	      // 1. 요청분석
	      // => id: session 에서 get
		  // => delete & session 처리
		String uri = "redirect:/home";

		//	2. Service & 결과처리
		if (service.delete(dto.getJno()) > 0) {
			rttr.addFlashAttribute("message","~~ 조 삭제 성공! ~~");
		} else {			
			rttr.addFlashAttribute("message","~~ 조 삭제 실패 ~~");
		}
		
		return uri;	
		
	}//delete
}
