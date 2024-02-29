package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Jo;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping(value="/jo")
@AllArgsConstructor
//	=> 모든 멤버변수를 초기화하는 생성자 자동 추가 & 사용
//	=> 그러므로 @Autowired 생략가능
public class JoController {
	MemberService mservice;
	JoService service;

	@GetMapping("/joList")
	public void mList(Model model) {
		model.addAttribute("banana", service.selectList());
		
	}	//joList
	
	@GetMapping("/joDetail")
	public String detail(Model model, @RequestParam("jno") int jno, Jo entity) {

		String uri = "jo/joDetail";	
		
		model.addAttribute("apple", service.selectOne(entity.getJno()));
		model.addAttribute("banana", mservice.findByJno(entity.getJno()));
		return uri;	
	}	//joDetail

//	@GetMapping("/detail")
//	public String detail(Model model, @RequestParam("jCode") String jCode, Jo entity) {
//
//		String uri = "jo/joDetail";	
//		
//		model.addAttribute("apple", service.selectOne(entity.getJno()));
//		
//		if ("U".equals(jCode))
//			uri="jo/joUpdate";
//		
//		if ("D".equals(jCode))	
//		model.addAttribute("banana", mservice.findByJno(entity.getJno()));
//		return uri;	
//	}	//joDetail

	@GetMapping("/joinForm")
	public void joinForm() {
	}	
	
	@PostMapping("/join")
	public String join(Model model, Jo entity) {

		String uri = "redirect:/jo/joList";	// 성공시

		try {
	    	 log.info("** Jo insert 성공 => \n" + service.save(entity));
	    	 model.addAttribute("message","~~ 조 등록 성공! ~~");
		} catch (Exception e) {
			log.info("** Jo insert Exception => \n" + e.toString());
			uri = "jo/joinForm";		
			model.addAttribute("message","~~ 조 등록 실패! ~~");
		}
	      return uri;
	}	//join
	
	@GetMapping("/joUpdate")
	public void joUpdate(@RequestParam("jno") int jno, Model model) {
		model.addAttribute( "apple", service.selectOne(jno));
	}	
	
	@PostMapping("/update")
	public String update(RedirectAttributes rttr, Model model, Jo entity) {
		
		String uri = "redirect:/jo/joList";	// 성공시
		
		model.addAttribute("apple" , entity);
		
		//	2. Service & 결과처리
		 try {
	    	 log.info("** Jo Update 성공 => \n" + service.save(entity));
	    	 rttr.addFlashAttribute("message","~~ 조 정보 수정 성공!! ~~");
		} catch (Exception e) {
			log.info("** Jo Update Exception => \n" + e.toString());
			uri = "jo/joUpdate";
			rttr.addFlashAttribute("message","~~ 조 정보 수정 오류!! 다시 하세요 ~~");
		}
	return uri;	

	}	//update
	
	@GetMapping("/delete")
	public String delete(RedirectAttributes rttr, int jno) {
	      // 1. 요청분석
	      // => id: session 에서 get
		  // => delete & session 처리
		String uri = "redirect:/jo/joList";
		
		//	2. Service & 결과처리
		try {
			 service.deleteById(jno);
			 log.info("** Jo Delete 성공 => \n" + jno);
			 rttr.addFlashAttribute("message","~~ 조 삭제 성공! ~~");
		} catch (Exception e) {
			 log.info("** Jo Delete Exception => \n" + e.toString());
			 rttr.addFlashAttribute("message","~~ 조 삭제 실패 ~~");
		}
		
		return uri;	

	}//delete
}
