package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/select")
public class Ex04_Select extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Ex04_Select() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String job = request.getParameter("job");
		String[] interest = request.getParameterValues("interest");
		 
      	response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<h2>**  Select Test  **</h2>");
		if ( job !=null && job.length() > 0) {
			out.print("<h3>**  당신의 직업 => "+ job + "</h3>");
		} else {
			out.print("<h3>**  직업을 선택하지 않았습니다.  **</h3>");
		}
		out.print("<h2>**  당신의 관심 분야  **</h2>");
		
		if (interest != null && interest.length > 0) {
			for (String s : interest) {
				out.printf("<h3>%s<h3>", s);
			}
		} else {
			out.print("<h3>선택 사항이 없음.</h3>");
		}
		out.print("<br><br><br><a href='javascript:history.go(-1)'>다시 입력하기</a>");
	}

}
