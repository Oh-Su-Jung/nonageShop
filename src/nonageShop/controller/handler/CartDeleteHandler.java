package nonageShop.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageShop.controller.Command;
import nonageShop.service.CartService;

public class CartDeleteHandler implements Command {
	private CartService service = new CartService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] noArr = request.getParameterValues("no");
		
		for(String no : noArr) {
			System.out.println(no);
			service.deleteCart(Integer.parseInt(no));
		}
		
		response.sendRedirect("cartList.do");
		
		return null;
	}

}
