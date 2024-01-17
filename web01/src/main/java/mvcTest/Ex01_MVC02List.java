package mvcTest;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcTest.StudentDTO;
import mvcTest.StudentService;


@WebServlet("/list2")
public class Ex01_MVC02List extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Ex01_MVC02List() {
        super();
    }
    
    //	** MVC 패턴1 StudentList 출력하기
	//	=> 요청 Service 처리
    //	=> 결과 출력(Java 스크립트)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StudentService service = new StudentService();
		List<StudentDTO> list = service.selectList();
		
		
		//	=> 결과 출력 : JSP, Java 스크립트
		//	=> Service 결과물을 JSP가 출력할 수 있도록 Attribute 만들어 보관
		//	  request.setAttibute(....) 
			request.setAttribute("myList", list); 
		//	=> Forward	  
			String uri="mvcTestJsp/ex01_MVC02List.jsp"; //ver01_Java_Scriptlet
			uri="mvcTestJsp/ex02_MVC02List.jsp"; //ver-2_JSTL
			request.getRequestDispatcher(uri).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}