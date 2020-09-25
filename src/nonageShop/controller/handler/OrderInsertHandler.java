package nonageShop.controller.handler;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageShop.controller.Command;
import nonageShop.dto.Cart;
import nonageShop.dto.Member;
import nonageShop.dto.OrderDetail;
import nonageShop.dto.Orders;
import nonageShop.service.CartService;
import nonageShop.service.OrderService;

public class OrderInsertHandler implements Command {
	private CartService cartService = new CartService();
	private OrderService orderService = new OrderService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "orderList.do";
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			url = "login.do";
		} else {
			Orders orders = getOrders(loginUser);
			int maxNo = orderService.addOrderAndDetail(orders);
			url = "orderList.do?no="+maxNo;
		}
		 //response.sendRedirect(url);
		
		return url;
	}
	
	private Orders getOrders(Member member) {
		ArrayList<OrderDetail> details = new ArrayList<OrderDetail>();
		for (Cart c : cartService.listCart(member)) {
			details.add(new OrderDetail(c));
		}
		Orders orders = new Orders();
		orders.setDetails(details);
		orders.setMember(member);
		return orders;
	}

}
