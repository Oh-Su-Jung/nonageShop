package nonageShop.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialArray;

import nonageShop.controller.Command;
import nonageShop.dto.Member;
import nonageShop.service.MemberService;

public class LoginHandler implements Command {
	private MemberService service = new MemberService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equalsIgnoreCase("get")) {
			
			return "/member/login.jsp";
		} else {
			String url="member/login_fail.jsp";
			HttpSession session = request.getSession();
			
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			
			Member member = service.getMember(id);

			System.out.println(member);
			
			if (member != null) {
				if (member.getPwd().equals(pwd)) {
					session.removeAttribute("id");
					session.setAttribute("loginUser", member);
					url="index.do";
				}
			}
			
			//request.getRequestDispatcher(url).forward(request, response);
			response.sendRedirect(url);
			
			return null;
		}
	}

}
