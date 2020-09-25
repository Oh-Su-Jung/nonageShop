package nonageShop.controller.handler;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageShop.controller.Command;
import nonageShop.dto.Address;
import nonageShop.service.AddressService;

public class FindZipNumHandler implements Command {
	private AddressService service = new AddressService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equalsIgnoreCase("get")) {
			System.out.println("get");
			
			return "/member/findZipNum.jsp";
		} else {
			System.out.println("post");
			
			String dong = request.getParameter("dong").trim();
			System.out.println(dong);
			
			ArrayList<Address> address = service.selectAddressByDong(dong);
			request.setAttribute("addressList", address);
			
			System.out.println(address);
			
			//response.sendRedirect("/member/findZipNum.jsp");
			request.getRequestDispatcher("/member/findZipNum.jsp").forward(request, response);
			
			return null;
		}
	}

}
