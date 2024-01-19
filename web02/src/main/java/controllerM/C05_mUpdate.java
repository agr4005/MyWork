package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/mupdate")
public class C05_mUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public C05_mUpdate() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//	1. 요청분석
			//	=> request의 한글( post 요청시 필수) & Parameter 처리
			//	=> 성공 : mdetail로(memberDetail.jsp)
			//	=> 실패 : 재수정 유도(updateForm.jsp) 
			//	=> 출력 객체(apple) 필요함
			//		-> redirect 또는 전달된 값들을 apple에 보관
			
			String uri = "member/memberDetail.jsp";
			request.setCharacterEncoding("UTF-8");
			
			MemberDTO dto = new MemberDTO();
			dto.setId(request.getParameter("id"));
			dto.setPassword(request.getParameter("password"));
			dto.setName(request.getParameter("name"));
			dto.setAge(Integer.parseInt(request.getParameter("age")));
			dto.setJno(Integer.parseInt(request.getParameter("jno")));
			dto.setInfo(request.getParameter("info"));
			dto.setPoint(Double.parseDouble(request.getParameter("point")));
			dto.setBirthday(request.getParameter("birthday"));
			dto.setRid(request.getParameter("rid"));
			
			//	=> 결과 출력을 위해 전달된 값들을 apple에 보관
			request.setAttribute("apple", dto);
			
			//	2. 서비스 처리
			//	=> Service 객체 생성 & 실행
			MemberService service = new MemberService();
			if (service.update(dto) > 0) {
				//	=> 성공	: session에 보관한 Name을 수정
				request.getSession().setAttribute("loginName", dto.getName());
				request.setAttribute("message", "~~ 회원 정보 수정 성공 !! ~~");
			} else {
				request.setAttribute("message", "~~ 회원 정보 수정 실패 !! 다시 시도해주세요 ~~");				
				uri = "member/updateForm.jsp";
			} 	//	=> 실패
			
		
			//	3. View (Response) : Forward
			request.getRequestDispatcher(uri).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
