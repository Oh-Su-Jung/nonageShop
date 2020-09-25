package nonageShop.controller.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import nonageShop.dto.Kind;
import nonageShop.dto.Product;
import nonageShop.service.ProductService;

@WebServlet("/KindListHandler")
public class KindListHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService service;

	public void init(ServletConfig config) throws ServletException {
		service = new ProductService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getMethod().equalsIgnoreCase("get")) {
			
		} else {
			List<Kind> kind = Arrays.asList(
					new Kind(1, "Heels"), 
					new Kind(2, "Boots"), 
					new Kind(3, "Sandals"), 
					new Kind(4, "Sneakers"), 
					new Kind(5, "On Sale")
					);
			
            Gson gson = new Gson(); 
            String result = gson.toJson(kind, new TypeToken<List<Kind>>(){}.getType());
                    
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            
            PrintWriter pw = response.getWriter();
            pw.print(result);
            pw.flush();
		}
	}

}
