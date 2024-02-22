package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.JoDTO;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(value="/jo")
@AllArgsConstructor
//	=> 모든 멤버변수를 초기화하는 생성자 자동 추가 & 사용
//	=> 그러므로 @Autowired 생략가능
public class JoController {

//	@Autowired // Autowired 적용시 개별적으로 적용해야함.
	JoService service;	// = new JoService();
//	@Autowired
	MemberService mservice;

	@RequestMapping(value = "/joList", method = RequestMethod.GET)
	public void mList(Model model) {
		model.addAttribute("banana", service.selectList());
	}	//joList
	
	@RequestMapping(value = "/joDetail", method = RequestMethod.GET)
	public String detail(Model model, @RequestParam("jno") int jno, JoDTO dto) {

		String uri = "jo/joDetail";	
		
//		** 조원 목록 출력하기 추가 (detail 출력시에만)
//		=> MemberService 실행
//		-> selectJoList 메서드 추가 : service, DAO
//		-> 실행결과는 banana로
		model.addAttribute("apple", service.selectOne(dto.getJno()));
		model.addAttribute("banana", mservice.selectJoList(dto.getJno()));
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
