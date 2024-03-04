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

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping(value="/member")
public class MemberController {
	
	MemberService service;
	PasswordEncoder passwordEncoder;	

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
	

	@GetMapping("/loginForm")
	public void loginForm(Model model) {
	}	
	
	@PostMapping("/login")
	public String login(HttpSession session, Model model, Member entity) {

	      String password = entity.getPassword();
	      String uri = "redirect:/home";	

	      entity = service.selectOne(entity.getId());
	      	//	PasswordEncoder 적용
	      	if (entity != null && 
	      			passwordEncoder.matches(password, entity.getPassword())) {
	    	// 성공
	    	  session.setAttribute("loginID", entity.getId());
	    	  session.setAttribute("loginName", entity.getName());
	      } else {
	    	// 실패
	    	  uri = "member/loginForm";
	    	  model.addAttribute("message","~~ id 또는 password 오류 !! 다시 하세요 ~~");
	      }
		return uri;
	}
	
	
	//	** Logout
	@GetMapping("logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();		
		return "redirect:/";
	}

	@GetMapping("/detail")
	public String detail(HttpSession session, Model model, @RequestParam("jCode") String jCode) {
	      // 1. 요청분석

		String id = (String)session.getAttribute("loginID");
		String uri = "member/memberDetail";	//detail

		if ("U".equals(jCode))
			uri = "member/updateForm";
		//	2. Service & 결과 처리
		
		model.addAttribute("apple", service.selectOne(id));
		return uri;	
	}//detail
	
	
	// ** Member List
	@GetMapping("/memberList")
	public void mList(Model model) {
		model.addAttribute("banana", service.selectList());
	}	//mList
	
	// ** JoinForm ************************************************
	@GetMapping("/joinForm")
	public void joinForm() {
		//model.addAttribute("banana", jservice.selectList());
	}	//joinForm
	
	//	** Join
	@PostMapping("/join")
	public String join(HttpServletRequest request,
			Model model, Member entity) throws IOException{
		//	1. 요청분석
		String uri = "member/loginForm";	// 성공시
		
	      // *** Upload File 처리 **************************
	      // 1) 물리적 실제저장위치 확인
		  // 1.1) 현재 웹어플리케이션의 실행위치 확인
		  // => 이클립스 개발환경 (배포전) : ~~ -tomcat- ~~ 포함되어있지 않음.
		  // => 톰캣 서버 배포 후: ~~ -tomcat- ~~ 포함
		  // realPath => E:\javaksb\IDESet\apache-tomcat-9.0.85\webapps\spring02\
	      String realPath = request.getRealPath("/");
	      log.info("** realPath => "+realPath);
	      realPath +="resources\\uploadImages\\";

	      // 1.2) 폴더 만들기 (없을수도 있음을 가정, File 실습)
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
	               = "E:\\javaksb\\MyWork\\demoJPA\\src\\main\\webapp\\resources\\images\\basicman1.jpg";
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
	      
	      // 1.3) 저장경로 완성
	      // => 기본 이미지 저장
	      String file1="", file2="basicman1.jpg";
	      
	      MultipartFile uploadfilef = entity.getUploadfilef();
	      if ( uploadfilef!=null && !uploadfilef.isEmpty() ) {
	         // => image_File 을 선택함  
	         // 1.3.1) 물리적위치 저장 (file1)
	         file1=realPath+uploadfilef.getOriginalFilename(); //저장경로(relaPath+화일명) 완성
	         uploadfilef.transferTo(new File(file1)); //해당경로에 저장(붙여넣기)
	         
	         // 1.3.2) Table 저장경로 완성 (file2)
	         file2 = uploadfilef.getOriginalFilename();
	      }
	      
	      entity.setUploadfile(file2);
	      // --------------------------------------------
		
		//	2. Service & 결과처리
		//	=> PasswordEncoder 적용
	      entity.setPassword(passwordEncoder.encode(entity.getPassword()));
	    //	** Service 처리  
	      try {
	    	 log.info("** member insert 성공 => \n" + service.save(entity));
	    	 model.addAttribute("message","회원가입 성공! 로그인후 이용하세요.");
		} catch (Exception e) {
			log.info("** member insert Exception => \n" + e.toString());
			uri="member/joinform";
			model.addAttribute("message","회원가입 실패! 재시도 해주세요.");
		}
	      return uri;
	}
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
	public String pwUpdate(HttpSession session, Model model, Member entity) {
		//	1) 요청 분석
		//	=> id : session에서 	
		//	=> password : 암호화
		entity.setId( (String)session.getAttribute("loginID") );
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		String uri ="member/loginForm";	//성공시
		
		//	2) Service
		  try {
			  service.updatePassword(entity.getId(), entity.getPassword());
		    	 log.info("** Password Update 성공 => " + entity.getId());
			model.addAttribute("message","Password 수정 성공! 재로그인 하세요.");
			session.invalidate();	
			} catch (Exception e) {
				log.info("** Password Update Exception => " + e.toString());
			model.addAttribute("message","Password 수정 실패! 재시도 해주세요.");
			uri = "member/pwUpdate";
			}
		
		return uri;
	}	//pwUpdate
	
	//	** Update
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, HttpSession session,
			Model model, Member entity) throws IOException {
		//	1. 요청분석
		//	=> 성공: memberDetail , 실패 : updateForm
		//	=> 두 경우 모두 출력하려면 dto 객체의 값("apple")이 필요하므로 보관
		
		String uri = "member/memberDetail";	// 성공시
		model.addAttribute("apple" , entity);
			
		//** uploadFile 처리
		//	=>	newImage 선택 여부
		//	=> 선택 -> oldImage 삭제, new Image 저장 : uploadfilef 사용
		//	=> 선택하지않음 -> oldImage가 uploadfile로 전달되었으므로 그냥 사용하면 됨.
		
		MultipartFile uploadfilef = entity.getUploadfilef();
	      if ( uploadfilef!=null && !uploadfilef.isEmpty() ) {
	         // => newImage를 선택함  
	         // 1) 물리적위치 저장 (file1)
		      String realPath = request.getRealPath("/");
		      String file1;
		      
		      // 2) realPath 를 이용해서 물리적저장위치 (file1) 확인
		      realPath +="resources\\uploadImages\\";
	    	  
		     //	3) oldFile 삭제
		     //	=> oldFile Name : dto.getUploadfile() 
		     //	=> 삭제경로 : realPath + dto.getUploadfile() 
		     File delFile = new File(realPath + entity.getUploadfile());
		     if (delFile.isFile())  delFile.delete(); //file 존재시 삭제
		     
		     //	4) newFile 저장  
	         file1=realPath+uploadfilef.getOriginalFilename(); //저장경로(relaPath+화일명) 완성
	         uploadfilef.transferTo(new File(file1)); //해당경로에 저장(붙여넣기)
	         
	         // 5) Table 저장경로 완성 (file2)
	         entity.setUploadfile(uploadfilef.getOriginalFilename());
	      }
		
		//	2. Service & 결과처리
	      
	      try {
		    	 log.info("** member Update 성공 => \n" + service.save(entity));
		    	 model.addAttribute("message","~~ 회원 정보 수정 성공!! ~~");
		    	 session.setAttribute("loginName", entity.getName());
			} catch (Exception e) {
				log.info("** member Update Exception => \n" + e.toString());
				uri = "member/updateForm";
				model.addAttribute("message","~~ 회원 정보 수정 오류!! 다시 하세요 ~~");
			}
		return uri;	
	}	//update
	
	//	** Delete
	@GetMapping("/delete")
	public String delete(HttpSession session, Model model, RedirectAttributes rttr) {
	      // 1. 요청분석
		String id = (String)session.getAttribute("loginID");
		String uri = "redirect:/home";
		
		//	2. Service & 결과처리
		try {
			 service.deleteById(id);
			 log.info("** member Delete 성공 => \n" + id);
			 rttr.addFlashAttribute("message","~~ 탈퇴 성공!! 1개월 후 재가입 가능합니다. ~~");
			 session.invalidate();
		} catch (Exception e) {
			 log.info("** member Delete Exception => \n" + e.toString());
			 rttr.addFlashAttribute("message","~~ 탈퇴 실패!! 관리자에게 연락하세요 ~~");
		}
		
		return uri;	
	}//delete
	
	// 2) Member_Jo Join List  
	// => ver01) @Query("...") 에 JPQL, LEFT_JOIN 구문, MemberDTO return
	// => MemberDTO 는 JoDTO 상속
//	   @GetMapping("/mjoinList")
//	   public void mjoinList(Model model) {
//	      model.addAttribute("banana", service.findMemberJoin());
//	   }
	   
	   @GetMapping("/mjoinList")
	   public void mjoinList(Model model) {
		   model.addAttribute("banana", service.findMemberJoin());
	   }
	
	
	
}	//class