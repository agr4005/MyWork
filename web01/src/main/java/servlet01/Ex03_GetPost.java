package servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/getpost")	//mapping name
public class Ex03_GetPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex03_GetPost() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	1)request의 parameter를 처리
		//	=> 한글처리, getParameter 전에 해야함
	    //   - Tomcat(WAS) 은 Get 방식요청에서는 "UTF-8" 을 default 로 적용함 
	    //   ( html 문서에서 "UTF-8" 작성되었고 , Get 방식으로 전송되면 생략가능
	    //     단, post 방식에서는 반드시 처리해야함 ) 
		request.setCharacterEncoding("UTF-8");  		
		String id = request.getParameter("id");
		//	=> name이 id인 input Tag의 value값을 return
		String name = request.getParameter("name");		
		
		//	=> 해당하는 Parameter가 없는 경우 : null을 return
		//	=> Parameter는 존재하지만 값이 없는 경우 : null이 아니며 값은 없음.
		//	(http://localhost:8080/web01/getpost?id=banana&name=바나나&password= )
		String password = request.getParameter("password");
		if (password != null && password.length() > 0) {			
			System.out.println("** password => " + password.toUpperCase());
		} else {
			System.out.println("** password is null or Empty ");			
		}
		
		//	** 출력문 (response 객체에 html 문서를 담아줌)
		//	=> 출력객체 생성 -> html 문서 작성
		//	=> 한글처리 해야함 (출력객체 생성전에 해야함.)
		
		response.setContentType("text/html; charset=UTF-8");
	    // => 웹브라우져에게 처리할 데이터의 MIME 타입을 알려줌
	    // => MIME : Multipurpose Internet Mail Extensions
	    // => 데이터 송.수신시 자료의 형식에 대한 정보 
	    // => Jsp 의 page 디렉티브의 contentType 속성값과 동일
		
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		out.print("<h2 style='color: blue;'> ** Get/Post Test ** </h2>");
		out.print("<h3> => 전달된 Parameter 확인 </h3>");
		out.print("<h3> => ID : " + id + "</h3>");
		out.print("<h3> => Name : " + name + "</h3>");
		out.print("</body></html>");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
