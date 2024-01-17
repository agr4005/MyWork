package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adder")
public class Ex01_Adder extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Ex01_Adder() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//		1) 요청분석
			//	=> request 처리 : 한글, Parameter 처리
		request.setCharacterEncoding("UTF-8"); 
		int num1 =  Integer.parseInt(request.getParameter("num1"));
		int num2 =  Integer.parseInt(request.getParameter("num2"));
			
			//	2) Service & 결과 처리
			//	=> response 한글처리, 출력객체 생성 & response에 담기
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
//		out.print("num1+num2 : " + (num1+num2) );
		out.printf("%d + %d = %d", num1,num2,(num1+num2));
	}

}
