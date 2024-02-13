package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

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
	
	//** Reply Insert **
	@GetMapping("/replyInsert")
	public void replyInsert(BoardDTO dto) {
		// => 답글처리를 위해 부모글의 root, step, indent를 인자로 전달받으면, 
		//	이 인자에 담겨진 값은 requestScope
		//	=> 그러므로 response 전송 전까지는 서버에서 사용 가능.
		//	단, 이 객체명의 첫 문자를 소문자로해서 접근가능 ( ${boardDTO.~~} )
	}

	//	=> 메서드명과 요청명이 위의 메서드와 동일하지만,
	//     Post 요청이고 인자가 다르기 때문에 허용됨.
	 @PostMapping("/replyInsert")
	 public String replyInsert(BoardDTO dto, Model model, RedirectAttributes rttr) {
		//	** 답글등록
		//	=> 성공시: boardList에서 입력완료 확인
		//	=> 실패시: replyInsert 재입력유도
	    String uri="redirect:boardList";
	    
	    //	=> dto 값 확인
	    //	-> id, title, content : 사용 가능
	    //	-> 부모글의 root : 사용 가능
	    //	-> 부모글의 step, indent : 1씩 증가
	    //	=> Sql 처리
	    //	- replyInsert, step의 Update
	    dto.setStep(dto.getStep()+1);
	    dto.setIndent(dto.getIndent()+1);
	    
	    if( service.rinsert(dto) > 0 ) {
	    	rttr.addFlashAttribute("message","~~ 답글등록 성공! ~~");
	    } else {
	    	uri="board/replyInsert";
	    	model.addAttribute("message","~~ 답글등록 실패! 다시 시도해주세요 ~~");
	    }
	        
		return uri;
	 }
	
	
	//** Board List
	@GetMapping("/boardList")
	public void boardList(Model model) {
		model.addAttribute("banana",service.selectList());
	}	//boardList
	
	//	** Board Detail
	//	=> 글요청 처리중, 글을 읽기전
	//	=> 조회수 증가
	//	-> session.loginID와 board의 id가 다른 경우
	@GetMapping("/detail")
	public String boardDetail(Model model, HttpSession session,
			@RequestParam("jCode") String jCode,
			@RequestParam("seq") int seq) {
		String uri="board/boardDetail";
		//	=> 조회수 증가 
		//	-> selectOne의 결과를 보관
		//	-> update 요청이 아니고, loginID와 글쓴이의 id가 다른 경우
		BoardDTO dto = service.selectOne(seq);
		
		if ("U".equals(jCode)) uri="board/boardUpdate";
		
		if ( !dto.getId().equals((String)session.getAttribute("loginID")) ) {			
			// 조회수 증가 조건 만족
			dto.setCnt(dto.getCnt()+1);
			service.update(dto);
		}

		model.addAttribute("apple", dto);

		return uri;
	}	//boardDetail
	
	//** Board Insert
	@GetMapping("/boardInsert")
	public void boardInsert(Model model, BoardDTO dto) {
	}
	
	
	@PostMapping("/insert")
	public String insert(Model model,  BoardDTO dto, RedirectAttributes rttr) {
		
		String uri = "redirect:/board/boardList";
		
		if(service.insert(dto) > 0) {
			rttr.addFlashAttribute("message","~~ 글쓰기 성공! ~~");
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
	public String update(RedirectAttributes rttr, Model model, BoardDTO dto) {
		
		String uri = "redirect:boardList";	// 성공시
		
		model.addAttribute("apple" , dto);
		
		if (service.update(dto) > 0) {
			rttr.addFlashAttribute("message","~~ 글 수정 성공! ~~");
		} else {
			uri = "board/boardUpdate";		
			model.addAttribute("message","~~ 글 수정 실패! 다시 시도하세요 ~~");
		}
		return uri;	
	}	//update
	
	@GetMapping("/delete")
	public String delete(RedirectAttributes rttr, BoardDTO dto) {

		String uri = "redirect:boardList";

		if (service.delete(dto) > 0) {
			rttr.addFlashAttribute("message","~~"+ dto.getSeq()+"번 글 삭제 성공! ~~");
		} else {			
			rttr.addFlashAttribute("message","~~"+ dto.getSeq()+"번 글 삭제 실패! ~~");
		}
		
		return uri;	
		
	}//delete
	
}	//class
