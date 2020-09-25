package nonageShop.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageShop.controller.Command;
import nonageShop.dto.Member;
import nonageShop.dto.Qna;
import nonageShop.service.QnaService;

public class QnaViewHandler implements Command {
	private QnaService service = new QnaService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/qna/qnaView.jsp";
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			url = "login.do";
		} else {
			int no = Integer.parseInt(request.getParameter("no"));
			Qna qna = service.getQna(no);
			request.setAttribute("qna", qna);
		}
		
		return url;
	}

}
