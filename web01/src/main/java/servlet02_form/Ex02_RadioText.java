package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/radio")
public class Ex02_RadioText extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Ex02_RadioText() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		String gender1 = request.getParameter("gender");
		String mail1 = request.getParameter("mailcheck");
		String content = request.getParameter("content");
			//	2) Service & 결과 처리
			//	=> response 한글처리, 출력객체 생성 & response에 담기
		if(mail1.equals("Yes")) mail1 = "수신동의";
		else mail1 = "수신거부";
		
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
	
		out.print("<pre>");
		out.print("<h2>성별: " + gender1+"</h2>");
		out.print("<h2>메일 수신: " + mail1+"</h2>");
		out.print("<h2>가입인사: " + content+"</h2>");
		out.print("<br><br><br><a href='javascript:history.go(-1)'>다시 입력하기</a>");
		out.print("<br><br><br><a href='javascript:history.go(-2)'>메인 화면</a>");
		out.print("</pre>");
		
	}

}
