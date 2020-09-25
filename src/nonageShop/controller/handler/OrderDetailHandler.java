package nonageShop.controller.handler;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageShop.controller.Command;
import nonageShop.dto.Member;
import nonageShop.dto.OrderDetail;
import nonageShop.dto.Orders;
import nonageShop.service.OrderService;

public class OrderDetailHandler implements Command {
	private OrderService service = new OrderService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/mypage/orderDetail.jsp";
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		
		if (loginUser == null) {
			url = "index.do";
		} else {
			int no = Integer.parseInt(request.getParameter("no"));
			
			Orders orderList = service.orderListByMember(loginUser.getId(), no, "1");
			
			int totalPrice = 0;
			for (OrderDetail od : orderList.getDetails()) {
				totalPrice += od.getCart().getPno().getSalePrice() * od.getCart().getQuantity();
			}
			System.out.println(orderList.getDetails().get(0));
			
			System.out.println(orderList);
			
			request.setAttribute("orderDetail", orderList.getDetails().get(0));
			request.setAttribute("orderList", orderList);
			request.setAttribute("totalPrice", totalPrice);
		}
		
		return url;
	}

}
