package nonageShop.controller.handler.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageShop.controller.Command;
import nonageShop.service.WorkerService;

public class AdminLoginHandler implements Command {
	private WorkerService service = new WorkerService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("1");
		String url = "admin.do";
		String msg = "";
		String workerId = request.getParameter("workerId").trim();
		String workerPwd = request.getParameter("workerPwd").trim();
		System.out.println("2");
		
		System.out.println("id : "+ workerId + "pwd : "+ workerPwd);
		
		int result = service.workerCheck(workerId, workerPwd);
		
		if (result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("workerId", workerId);
			url = "adminProductList.do";
		} else if (result == 0) {
			msg = "비밀번호를 확인하세요.";
		} else if (result == -1) {
			msg = "아이디를 확인하세요.";
		}
		
		request.setAttribute("message", msg);
		
		return url;
	}

}
