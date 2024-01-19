package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/login")
public class C02_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public C02_Login() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	1. 요청분석
		//	=> request의 Parameter 처리
		//	=> id, password 처리
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String uri = "home.jsp";
			
		//	2. 서비스 처리
		//	=> Service, DTO 객체 생성
		//	=> id 확인 : Service의 SelectOne
		//	=> id 확인되면 password 일치 여부 확인
		//	=> 성공: id, name을 session에 보관, home으로
		//	=> 실패: loginForm으로 재로그인 유도
		MemberService service = new MemberService();
		MemberDTO dto = service.selectOne(id);
		if(dto != null && dto.getPassword().equals(password)) {
			request.getSession().setAttribute("loginID", dto.getId());
			request.getSession().setAttribute("loginName", dto.getName());
		} else {
			uri = "member/loginForm.jsp";
			request.setAttribute("message", "로그인 실패! 다시 시도해주세요!");
		}
		
		//	3. View (Response) : Forward
		request.getRequestDispatcher(uri).forward(request, response);
	}	//doPost
}
