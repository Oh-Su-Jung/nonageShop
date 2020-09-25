package nonageShop.controller.handler;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import nonageShop.controller.Command;
import nonageShop.dto.Member;
import nonageShop.service.MemberService;

public class JoinHandler implements Command {
	MemberService service = new MemberService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equalsIgnoreCase("get")) {
			System.out.println("get");

			return "/member/join.jsp";
		} else {
			System.out.println("post");
			Gson gson = new Gson();
			Member member = gson.fromJson(new InputStreamReader(request.getInputStream(), "UTF-8"), Member.class);
			System.out.println(member);
			
			int res = service.insertMember(member);
			response.getWriter().print(res);
			
			return null;
		}
	}

}
