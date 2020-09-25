package nonageShop.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageShop.controller.Command;
import nonageShop.dto.Cart;
import nonageShop.dto.Member;
import nonageShop.dto.Product;
import nonageShop.service.CartService;

public class CartInsertHandler implements Command {
	private CartService service = new CartService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "cartList.do";
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");

		if (loginUser == null) {
			url = "login.do";
		} else {
			Cart cart = new Cart();
			cart.setMemberId(new Member(loginUser.getId()));
			cart.setPno(new Product(Integer.parseInt(request.getParameter("no"))));
			cart.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			
			service.insertCart(cart);
		}
		response.sendRedirect(url);
		
		return null;
	}

}
