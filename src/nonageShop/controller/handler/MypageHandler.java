package nonageShop.controller.handler;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageShop.controller.Command;
import nonageShop.dto.Member;
import nonageShop.dto.Orders;
import nonageShop.service.OrderService;

public class MypageHandler implements Command {
	private OrderService service = new OrderService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/mypage/mypage.jsp";
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			url = "login.do";
		} else {
			ArrayList<Integer> noList = service.selectSeqOrderIng(loginUser);
			ArrayList<Orders> orderList = new ArrayList<Orders>();
			System.out.println("test");
			
			for (int no : noList) {
				orderList.add(service.orderListByMember(loginUser.getId(), no, "1"));
			}
			
			request.setAttribute("title", "진행 중인 주문 내역");
			request.setAttribute("orderList", orderList);
		}
		
		return url;
	}

}
