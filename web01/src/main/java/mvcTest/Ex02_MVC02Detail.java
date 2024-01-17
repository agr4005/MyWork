package mvcTest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/myinfo")
public class Ex02_MVC02Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Ex02_MVC02Detail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int sno=(Integer)request.getSession().getAttribute("loginID");
		StudentService service = new StudentService();
		StudentDTO dto = service.selectOne(sno);
		request.getSession().setAttribute("myInfo", dto); 
		
		String uri="mvcTestJsp/ex03_MVC02Detail.jsp";
		request.getRequestDispatcher(uri).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
