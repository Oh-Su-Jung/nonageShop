package nonageShop.controller.handler;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageShop.controller.Command;
import nonageShop.dto.Product;
import nonageShop.service.ProductService;

public class CatagoryHandler implements Command {
	private ProductService service = new ProductService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equalsIgnoreCase("get")) {
			String kind = request.getParameter("kind");
			ArrayList<Product> product = service.listKindProduct(kind);
			request.setAttribute("productKindList", product);
			
			System.out.println(kind);
			System.out.println(product);
			
			return "/product/productKind.jsp";
		} else {

			return null;	
		}
	}

}
