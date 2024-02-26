package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.MemberDTO;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pageTest.PageMaker;
import pageTest.SearchCriteria;

@Log4j2 // @Log4j -> Boot에서는 2015년 이후 지원 중단
@AllArgsConstructor	//개별적인 Autowired 생략 가능 
@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	MemberService service;
	PasswordEncoder passwordEncoder;	//demoConfig에 설정
	
	JoService jservice;
	
	@GetMapping("/aximlist")
	public String aximlist(Model model) {
		model.addAttribute("banana",service.selectList());
		return "axTest/axMemberList";
	}	

	//	** Ajax Member_Paging
	// => ver01 : axmcri만 구현 (search 기능만 구현)
	// => ver02 : "axmcheck" 요청도 처리할 수 있도록 구현
	//		-> mappingName에 "check"가 포함되어 있으면 service를 아래의 메서드로 처리하도록
	//		service.mCheckList(cri), mCheckRowCount(cri)
	@GetMapping({"/axmcri","/axmcheck"})
	public String axmcri(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {
		// 1) Criteria 처리
		cri.setSnoEno();
		
		//	2) 요청 확인 & Service 처리
		String mappingName =
				request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		pageMaker.setMappingName(mappingName);
		pageMaker.setCri(cri);
		
		if (mappingName.contains("check")) {
			model.addAttribute("banana", service.mCheckList(cri));			
			pageMaker.setTotalRowsCount(service.totalRowsCount(cri));			
		} else {
			model.addAttribute("banana", service.mPageList(cri));			
			pageMaker.setTotalRowsCount(service.totalRowsCount(cri));			
		}
		
		//	3) View 처리 : PageMaker 이용
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "axTest/axmPageList";
	}	//mPageList
	
	
	
	@GetMapping("/log4jTest")
	public String log4jTest() {
		String name="banana";
		log.error("Lombok @Log4j Test Error: name = "+ name);
		log.warn("Lombok @Log4j Test WARN: name = "+ name);
		log.info("Lombok @Log4j Test INFO: name = "+ name);
		log.debug("Lombok @Log4j Test DEBUG: name = "+ name);
		log.trace("Lombok @Log4j Test TRACE: name = "+ name);

		return "redirect:/";
	}
	
	//	** Member Check List
	@GetMapping("/mCheckList")
	public String mCheckList(HttpServletRequest request, Model model, 
			SearchCriteria cri, PageMaker pageMaker) {
		
		String uri="member/mPageList";
		
		// 1) Criteria 처리
		cri.setSnoEno();
		
		//	2) Service
		if ( cri.getCheck() !=null && cri.getCheck().length < 1) 
			cri.setCheck(null);
		
		model.addAttribute("banana", service.mCheckList(cri));
		
		//	3) View 처리 : PageMaker 이용
		String mappingName =
				request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);
		pageMaker.setTotalRowsCount(service.mCheckRowsCount(cri));
		model.addAttribute("pageMaker", pageMaker);
		return uri;
	}	//mCheckList
	
	
	//** Member_Paging
	@GetMapping("/mPageList")
	public void mPageList(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {
	    // 1) Criteria 처리
	    cri.setSnoEno();
		
		//	2) Service
		model.addAttribute("banana", service.mPageList(cri));
		
		//	3) View 처리 : PageMaker 이용
		String mappingName =
				request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);
		pageMaker.setTotalRowsCount(service.totalRowsCount(cri));
		model.addAttribute("pageMaker", pageMaker);

	}	//mPageList
	
	
//	** ID중복확인
	@GetMapping("/idDupCheck")
	public void idDupCheck(@RequestParam("id") String id,Model model) {
		//	1) newID 존재여부 확인 & 결과처리
		if (service.selectOne(id) != null) {
			model.addAttribute("idUse", "F");
		} 	//	=> 이미 자료가 존재하므로 사용 불가능
		else {
			model.addAttribute("idUse", "T");
			
		}	//	=> 사용가능
	}
	// ** loginForm
	
//	** Login Form 출력	
	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public void loginForm(Model model) {
	}	//loginForm
	
	@PostMapping("/login")
	public String login(HttpSession session, Model model, MemberDTO dto) {
	      String password = dto.getPassword();
	      String uri = "redirect:/home";		//성공시
		
		//	2. 서비스 처리
		//	=> id 확인
		//	=> 존재하면 password 확인
		//	=> 성공: id, name은 session에 보관, home으로
		//	=> 실패: 재로그인 유도
	      dto = service.selectOne(dto.getId());
	      	//	PasswordEncoder 적용
	     // if (dto != null && dto.getPassword().equals(password)) {
	      	if (dto != null && 
	      			passwordEncoder.matches(password, dto.getPassword())) {
	    	// 성공
	    	  session.setAttribute("loginID", dto.getId());
	    	  session.setAttribute("loginName", dto.getName());
	      } else {
	    	// 실패
	    	  uri = "member/loginForm";
	    	  model.addAttribute("message","~~ id 또는 password 오류 !! 다시 하세요 ~~");
	      }
		return uri;
	}
	
	
	//	** Logout
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session, Model model) {
		session.invalidate();		
		return "redirect:/";
	}
	
	
	   // ** Member Detail
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(HttpSession session, Model model, @RequestParam("jCode") String jCode) {
	      // 1. 요청분석
	      // => id: session 에서 get
	      // => detail 또는 수정 Page 출력을 위한 요청인지 jCode 로 구별 
		String id = (String)session.getAttribute("loginID");
		String uri = "member/memberDetail";	//detail
		
		//	=> update 요청 확인후 uri 수정
		if ("U".equals(jCode))
			uri = "member/updateForm";
		//	2. Service & 결과 처리
		
		model.addAttribute("apple", service.selectOne(id));
		return uri;	
	}//detail
	
	
	// ** Member List
	@RequestMapping(value = "/memberList", method = RequestMethod.GET)
	public void mList(Model model) {
		model.addAttribute("banana", service.selectList());
	}	//mList
	
	// ** JoinForm ************************************************
	@RequestMapping(value = "/joinForm", method = RequestMethod.GET)
	public void joinForm() {
	}	//joinForm
	 
	
	//	** Join
	@PostMapping("/join")
	public String join(HttpServletRequest request,
			Model model, MemberDTO dto) throws IOException{
		//	1. 요청분석
		//	=> 이전: 한글처리, request 값 -> dto에 set
		//	=> 스프링: 한글은 filter, request 처리는 매개변수로 자동화
		String uri = "member/loginForm";	// 성공시

	      // 1) 물리적 실제저장위치 확인
		  // 1.1) 현재 웹어플리케이션의 실행위치 확인
		  // => 이클립스 개발환경 (배포전) : ~~ -tomcat- ~~ 포함되어있지 않음.
		  // => 톰캣 서버 배포 후: ~~ -tomcat- ~~ 포함
		  // realPath => E:\javaksb\MyWork\demoM\src\main\webapp\
	      String realPath = request.getRealPath("/");
	      System.out.println("** realPath => "+realPath);
	      // Spring Boot의 경우 : realPath=> E:\javaksb\MyWork\demoM\src\main\webapp\
	      
	      // 1.2) realPath 를 이용해서 물리적저장위치 (file1) 확인
	      if (! realPath.contains("-tomcat-") ) // 개발중
	          realPath +="resources\\uploadImages\\";
	      else realPath ="E:\\javaksb\\IDESet\\apache-tomcat-9.0.85\\webapps\\demoM\\resources\\uploadImages\\";

	      // 1.3) 폴더 만들기 (없을수도 있음을 가정, File 실습)

	      File file = new File(realPath);
	      if ( !file.exists() ) {
	         // => 저장폴더가 존재하지 않는경우 만들어줌
	         file.mkdir();
	      }
	      
	      // --------------------------------------------
	      // ** File Copy 하기 (IO Stream 실습)
	      // => 기본이미지(basicman1.png) 가 uploadImages 폴더에 없는경우 기본폴더(images) 에서 가져오기
	      // => IO 발생: Checked Exception 처리
	      file = new File(realPath + "basicman1.jpg"); // uploadImages 폴더에 화일존재 확인을 위함
	      if ( !file.isFile() ) { // 존재하지않는 경우
	         String basicImagePath 
	               = "E:\\javaksb\\MyWork\\demoM\\src\\main\\webapp\\resources\\images\\basicman1.jpg";
	         FileInputStream fi = new FileInputStream(new File(basicImagePath));
	         // => basicImage 읽어 파일 입력바이트스트림 생성
	         FileOutputStream fo = new FileOutputStream(file); 
	         // => 목적지 파일(realPath+"basicman1.jpg") 출력바이트스트림 생성  
	         FileCopyUtils.copy(fi, fo);
	      }
	      // --------------------------------------------
	      // ** MultipartFile
	      // => 업로드한 파일에 대한 모든 정보를 가지고 있으며 이의 처리를 위한 메서드를 제공한다.
	      //    -> String getOriginalFilename(), 
	      //    -> void transferTo(File destFile),
	      //    -> boolean isEmpty()
	      
	      // 1.4) 저장경로 완성
	      // => 기본 이미지 저장
	      String file1="", file2="basicman1.jpg";
	      
	      MultipartFile uploadfilef = dto.getUploadfilef();
	      if ( uploadfilef!=null && !uploadfilef.isEmpty() ) {
	         // => image_File 을 선택함  
	         // 1.4.1) 물리적위치 저장 (file1)
	         file1=realPath+uploadfilef.getOriginalFilename(); //저장경로(relaPath+화일명) 완성
	         uploadfilef.transferTo(new File(file1)); //해당경로에 저장(붙여넣기)
	         
	         // 1.4.2) Table 저장경로 완성 (file2)
	         file2 = uploadfilef.getOriginalFilename();
	      }
	      
	      dto.setUploadfile(file2);
	      // --------------------------------------------
		
		//	2. Service & 결과처리
		//	=> PasswordEncoder 적용
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		// ** *****************************************
	    // ** Transaction_AOP 적용 ********************* 
	    // 1. 준비: pom.xml (dependency) 확인
	    // =>  AspectJ(기본제공), AspectJ Weaver(추가)
	      
	    // 2. servlet-context.xml AOP 설정
	      
	    // 3. Rollback Test
	    // 3.1) Aop xml 적용전 => insert1 은 입력되고, insert2 에서  500_Dupl..Key  오류 발생
	    // 3.2) Aop xml 적용후 => insert2 에서 오류발생시 모두 Rollback 되어 insert1, insert2 모두 입력 안됨 
	      
	    // 3.1) Transaction 적용전 : 동일자료 2번 insert
	    // => 첫번째는 입력완료(commit) 되고, 두번째자료 입력시 Key중복 오류발생 (500 발생)
	    // 3.2) Transaction 적용후 : 동일자료 2번 insert
	    // => 첫번째는 입력완료 되고, 두번째 자료입력시 Key중복 오류발생 하지만,
	    //    rollback 되어 둘다 입력 안됨
	      
		//service.insert(dto); // Transaction_Test, insert1 
		
		if (service.insert(dto) > 0) {
			// 성공시
			model.addAttribute("message","~~ 회원 가입 성공!! 로그인 후 이용하세요 ~~");
		} else {
			// 실패시
			uri = "member/joinForm";		
			model.addAttribute("message","~~ 회원 가입 실패!! 다시 하세요 ~~");
		}
		return uri;	
	}	//join
	
	//	** Password 수정 (PasswordEncoder 추가후)
	@GetMapping("/pwUpdate")
	public void pwUpdate() {
		//View_name 생략
	}
	
	//	** PasswordUpdate
	//	=> Service, DAO에 pwUpdate(dto) 메서드 추가
	//	=> 성공: session 무효화, 로그인창으로
	//	   실패: pwUpdate , 재수정 유도
	@PostMapping("/pwUpdate")
	public String pwUpdate(HttpSession session, Model model, MemberDTO dto) {
		//	1) 요청 분석
		//	=> id : session에서 	
		//	=> password : 암호화
		dto.setId( (String)session.getAttribute("loginID") );
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		String uri ="member/loginForm";	//성공시
		//	2) Service
		if (service.pwUpdate(dto) > 0) {
			model.addAttribute("message","Password 수정 성공! 재로그인 하세요.");
			session.invalidate();	
		} else {
			model.addAttribute("message","Password 수정 실패! 재시도 해주세요.");
			uri = "member/pwUpdate";
		}
		return uri;
	}	//pwUpdate
	
	//	** Update
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, HttpSession session,
			Model model, MemberDTO dto) throws IOException {
		//	1. 요청분석
		//	=> 성공: memberDetail , 실패 : updateForm
		//	=> 두 경우 모두 출력하려면 dto 객체의 값("apple")이 필요하므로 보관
		
		String uri = "member/memberDetail";	// 성공시
		model.addAttribute("apple" , dto);
			
		//** uploadFile 처리
		//	=>	newImage 선택 여부
		//	=> 선택 -> oldImage 삭제, new Image 저장 : uploadfilef 사용
		//	=> 선택하지않음 -> oldImage가 uploadfile로 전달되었으므로 그냥 사용하면 됨.
		
		MultipartFile uploadfilef = dto.getUploadfilef();
	      if ( uploadfilef!=null && !uploadfilef.isEmpty() ) {
	         // => newImage를 선택함  
	         // 1) 물리적위치 저장 (file1)
		      String realPath = request.getRealPath("/");
		      String file1;
		      
		      // 2) realPath 를 이용해서 물리적저장위치 (file1) 확인
		      if (! realPath.contains("-tomcat-") ) // 개발중
		    	  realPath +="resources\\uploadImages\\";
		      else realPath ="E:\\javaksb\\IDESet\\apache-tomcat-9.0.85\\webapps\\demoM\\resources\\uploadImages\\";

	    	  
		     //	3) oldFile 삭제
		     //	=> oldFile Name : dto.getUploadfile() 
		     //	=> 삭제경로 : realPath + dto.getUploadfile() 
		     File delFile = new File(realPath + dto.getUploadfile());
		     if (delFile.isFile())  delFile.delete(); //file 존재시 삭제
		     
		     //	4) newFile 저장  
	         file1=realPath+uploadfilef.getOriginalFilename(); //저장경로(relaPath+화일명) 완성
	         uploadfilef.transferTo(new File(file1)); //해당경로에 저장(붙여넣기)
	         
	         // 5) Table 저장경로 완성 (file2)
	         dto.setUploadfile(uploadfilef.getOriginalFilename());
	      }
		
		//	2. Service & 결과처리
		if (service.update(dto) > 0) {
			model.addAttribute("message","~~ 회원 정보 수정 성공!! ~~");
			//	=> name을 수정할수도 있으므로 loginName을 수정해준다
			session.setAttribute("loginName", dto.getName());
		} else {
			// 실패 : 재수정 유도
			uri = "member/updateForm";		
			model.addAttribute("message","~~ 회원 정보 수정 오류!! 다시 하세요 ~~");
		}
		return uri;	
	}	//update
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpSession session, Model model, RedirectAttributes rttr) {
	      // 1. 요청분석
	      // => id: session 에서 get
		  // => delete & session 처리
		String id = (String)session.getAttribute("loginID");
		String uri = "redirect:/home";
		
		//	2. Service & 결과처리
		if (service.delete(id) > 0) {
			//	model.addAttribute("message","~~ 탈퇴 성공!! 1개월 후 재가입 가능합니다. ~~");
			//	=> requestScope의 message를 redirect시에도 유지하려면
			//	session에 보관했다가 사용후에는 삭제해야함
			//	session 에 보관후 redirect 되어진 요청 처리시에 requestScope에 옮기고
			//	session의 message는 삭제
			//	=> 이것을 처리해주는 API가 RedirectAttributes
			rttr.addFlashAttribute("message","~~ 탈퇴 성공!! 1개월 후 재가입 가능합니다. ~~");
			session.invalidate();
		} else {			
			//	model.addAttribute("message","~~ 탈퇴 실패!! 관리자에게 연락하세요 ~~");
			rttr.addFlashAttribute("message","~~ 탈퇴 실패!! 관리자에게 연락하세요 ~~");
		}
		
		return uri;	
		
	}//delete
	
}	//class