package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/mdelete")
public class C06_mDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public C06_mDelete() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//	1. 요청분석
			//	=> request의 한글( post 요청시 필수) & Parameter 처리
			//	=> 성공 : mdetail로(memberDetail.jsp)
			//	=> 실패 : 재수정 유도(updateForm.jsp) 
			//	=> 출력 객체(apple) 필요함
			//		-> redirect 또는 전달된 값들을 apple에 보관
			String id = (String)request.getSession().getAttribute("loginID");
			String uri = "home.jsp";
			//	2. 서비스 처리
			//	=> Service 객체 생성 & 실행
			MemberService service = new MemberService();
			if (service.delete(id) > 0) {
				request.setAttribute("message", id+"님 탈퇴 성공");
				request.getSession().invalidate();
			} else {				
				request.setAttribute("message", id+"님 탈퇴 실패");
			}
			
			//	3. View (Response) : Forward
			request.getRequestDispatcher(uri).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
