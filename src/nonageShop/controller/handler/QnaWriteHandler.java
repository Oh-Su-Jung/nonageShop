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

public class QnaWriteHandler implements Command {
	private QnaService service = new QnaService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/qna/qnaWrite.jsp";
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			url = "login.do";
		}
		
		if (request.getMethod().equalsIgnoreCase("post")) {
			url = "qnaList.do";
			Qna qna = new Qna();
			qna.setSubject(request.getParameter("subject"));
			qna.setContent(request.getParameter("content"));
			qna.setId(loginUser.getId());
			
			int res = service.insertQna(qna);
			
			response.getWriter().print(res);
		}
		
		return url;
	}

}
