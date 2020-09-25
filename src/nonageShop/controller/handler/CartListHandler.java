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
import nonageShop.service.CartService;

public class CartListHandler implements Command {
	private CartService service = new CartService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/mypage/cartList.jsp";

		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			url = "login.do";
		} else {
			ArrayList<Cart> cartList = service.listCart(new Member(loginUser.getId()));
			
			int totalPrice = 0;
			if (cartList != null) {
				for (Cart cart : cartList) {
					totalPrice += cart.getPno().getSalePrice() * cart.getQuantity();
				}	
			} else {
				cartList = new ArrayList<Cart>();
			}
			
			request.setAttribute("cartList", cartList);
			request.setAttribute("totalPrice", totalPrice);
			
			url = "/mypage/cartList.jsp";
		}
		
		return url;
	}

}
