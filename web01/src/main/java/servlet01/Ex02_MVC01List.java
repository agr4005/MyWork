package servlet01;

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


@WebServlet(urlPatterns ="/list")
public class Ex02_MVC01List extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Ex02_MVC01List() {
        super();
    }
    
    //	** MVC 패턴1 StudentList 출력하기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	=> 요청 Service 처리
		
		StudentService service = new StudentService();
		List<StudentDTO> list = service.selectList();
		// 객체가 서버의 메모리에서 유지되는 시간(scope)
		// scope의 4종류(request, page, session, application)
		// request: response 될때까지(요청 하나하나) / page : 매핑 메서드가 실행되는 동안 유지
		// session: 개별 유저(쿠키-세션아이디로 구분) 
		// application : 어플리케이션 종료전까지 유지
		
		
		//	=> 결과 출력 : 출력 내용을 Response 객체의 Body 영역에 write
		// 	- 한글처리
		//	- 출력객체 생성 & 출력
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print("<html><body>");
		out.print("<h2 style='color: blue;'> ** Servlet_MvC1 StudentList ** </h2>");
		out.print("<table border='1'><tr><th>Sno</th><th>Name</th><th>Age</th>");
		out.print("<th>Jno</th><th>Info</th><th>point</th><tr>");
		
		
		if(list != null) {
			
			for (StudentDTO s:list) {
				//out.print(s+"<br>");
				out.print("<tr><td>"+s.getSno()+"</td>");
				out.print("<td>"+s.getName()+"</td>");
				out.print("<td>"+s.getAge()+"</td>");
				out.print("<td>"+s.getJno()+"</td>");
				out.print("<td>"+s.getInfo()+"</td>");
				out.print("<td>"+s.getPoint()+"</td></tr>");
			}
		} else {
			out.print("<h2>~~ 출력할 Data가 없습니다. ~~</h2>");
		}
		out.print("</table></body></html>");

		
	
		
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}



//		response.getWriter().append("Served at: ").append(request.getContextPath());