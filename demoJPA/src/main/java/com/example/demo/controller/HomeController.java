package com.example.demo.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.GuestbookDTO;
import com.example.demo.domain.PageRequestDTO;
import com.example.demo.domain.PageResultDTO;
import com.example.demo.entity.Guestbook;
import com.example.demo.service.GuestbookService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	@Autowired
	GuestbookService service;
	
	@GetMapping("/home")
	// @GetMapping(value={"/", "/home"})
	// => void : 요청명.jsp 를 viewName 으로 처리함 (home.jsp)
	//           그러므로 "/" 요청은 .jsp 를 viewName 으로 찾게됨(제외) 
	// => Boot 의 매핑메서드 에서 "/" 요청은 적용안됨(무시됨) 
	//     WebMvcConfig 의 addViewControllers 메서드로 해결
	public void home(Locale locale, Model model) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
	}	//home
	
	@GetMapping("/axtestform")
	public String axTestForm() {
		
		return "axTest/axTestForm";
	}
	
	@GetMapping("/ginsert")
	public String ginsert() {
		
		GuestbookDTO dto = GuestbookDTO.builder()
							.title("JPA Insert Test")
							.content("입력이 술술 잘됩니다.")
							.writer("admin")
							.build();
		System.out.println("** guest Insert => " + service.register(dto));
		
		return "redirect:home";
	}
	
	@GetMapping("/glist")
	public String glist() {
		
		List<Guestbook> list = service.selectList();
		for (Guestbook g : list) {
			System.out.print(g+", regDate= "+g.getRegDate()+", modDate= " + g.getModDate());
			System.out.println("");
		}
		
		return "redirect:home";
	}
	
	@GetMapping("/gupdate")
	public String gupdate() {
		
		GuestbookDTO dto = GuestbookDTO.builder()
							.gno(5L)
							.title("JPA Update Test")
							.content("수정이 술술 잘됩니다.")
							.writer("banana")
							.build();
		System.out.println("** guest Update => " + service.register(dto));
		
		return "redirect:home";
	}
	
	@GetMapping("/gdetali")
	//	=> 쿼리스트링으로 Test : /gdetali?gno=2
	public String gdetail(Long gno) {
		System.out.println(" ** gdetail => " + service.selectOne(gno));
		return "redirect:home";
	}
	@GetMapping("/gdelete")
	//	=> 쿼리스트링으로 Test : /gdelete?gno=5
	public String gdelete(Long gno) {
		try {
			service.delete(gno);
			System.out.println(" ** gDelete 성공 => "+ gno);
			} catch (Exception e) {
			System.out.println("** gDelete Exception => " + e.toString());
			// => 자료가 없으면 org.springframework.dao.EmptyResultDataAccessException 발생확인
		}
		return "redirect:home";
	}
	
	//	** JPA Paging & Sort
	//	=> PageRequestDTO(페이징 조건들) -> PageRequestDTO(최종결과)
	//	=> 사용객체들 : Page<Entity>, Pageable(i) -> PageRequest(구현체) 등
	@GetMapping("/gpage")
	public String gpage() {
		PageRequestDTO requestDTO = PageRequestDTO.builder()
		.page(2).size(5).build();
		//	=> 출력할 PageNo, Page당 출력할 row갯수 입력
		
		PageResultDTO<GuestbookDTO, Guestbook> resultDTO =
				service.pageList(requestDTO);
		System.out.println("** Page List => " + requestDTO.getPage());
		for( GuestbookDTO g: resultDTO.getDtoList() ) {
			System.out.println(g);
		}
		
		return "redirect:home";
	}
	
} //class
