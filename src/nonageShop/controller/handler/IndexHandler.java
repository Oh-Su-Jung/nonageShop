package nonageShop.controller.handler;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageShop.controller.Command;
import nonageShop.dto.Product;
import nonageShop.service.ProductService;

public class IndexHandler implements Command {
	private ProductService service = new ProductService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getMethod().equalsIgnoreCase("get")) {
			ArrayList<Product> newList = service.listNewProduct();
			ArrayList<Product> bestList = service.listBestProduct();
			
			request.setAttribute("newProductList", newList);
			request.setAttribute("bestProductList", bestList);
			
			return "/index.jsp";
			
		} else {
			return "/index.jsp";
		}
	}

}
