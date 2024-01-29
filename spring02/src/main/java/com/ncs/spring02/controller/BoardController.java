package com.ncs.spring02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.service.BoardService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	BoardService service;
	
	//** Board List
	@GetMapping("/boardList")
	public void boardList(Model model) {
		model.addAttribute("banana",service.selectList());
	}	//boardList
	
	//** Board Detail
	@GetMapping("/detail")
	public String boardDetail(Model model, @RequestParam("jCode") String jCode,
			@RequestParam("seq") int seq, BoardDTO dto) {
		
		String uri="board/boardDetail";
		if ("U".equals(jCode)) uri="board/boardDetail";

		model.addAttribute("apple", service.selectOne(seq));
		
		return uri;
	}	//boardDetail
	
	//** Board Insert
	@GetMapping("/boardInsert")
	public void boardInsert(Model model, BoardDTO dto) {
		model.addAttribute( "apple", service.insert(dto));
	}
	
	@PostMapping("/insert")
	public String insert(Model model,  BoardDTO dto) {
		
		String uri = "redirect:/board/boardList";
		
		if(service.insert(dto) > 0) {
			model.addAttribute("message","~~ 글쓰기 성공! ~~");
		} else {
			uri = "board/boardInsert";
			model.addAttribute("message","~~ 글쓰기 실패! 다시 시도하세요 ~~");
		}
		return uri;
	}
	
	@GetMapping("/boardUpdate")
	public void boardUpdate(@RequestParam("seq") int seq, Model model) {
		model.addAttribute( "apple", service.selectOne(seq));
	}	
	
	@PostMapping("/update")
	public String update( Model model, BoardDTO dto) {
		
		String uri = "redirect:/board/boardList";	// 성공시
		
		model.addAttribute("apple" , dto);
		
		//	2. Service & 결과처리
		if (service.update(dto) > 0) {
			model.addAttribute("message","~~ 글 수정 성공! ~~");
		} else {
			// 실패 : 재수정 유도
			uri = "board/boardUpdate";		
			model.addAttribute("message","~~ 글 수정 실패! 다시 시도하세요 ~~");
		}
		return uri;	
	}	//update
	
	@GetMapping("/delete")
	public String delete(Model model, RedirectAttributes rttr, BoardDTO dto) {

		String uri = "redirect:/board/boardList";

		if (service.delete(dto.getSeq()) > 0) {
			rttr.addFlashAttribute("message","~~ 글 삭제 성공! ~~");
		} else {			
			rttr.addFlashAttribute("message","~~ 글 삭제 실패 ~~");
		}
		
		return uri;	
		
	}//delete
	
	
	
	
	
}	//class
