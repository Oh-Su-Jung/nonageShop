package nonageShop.service;

import java.sql.Connection;
import java.util.ArrayList;

import nonageShop.dao.impl.CartDaoImpl;
import nonageShop.ds.JndiDS;
import nonageShop.dto.Cart;
import nonageShop.dto.Member;

public class CartService {
	private CartDaoImpl dao = CartDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public CartService() {
		dao.setCon(con);
	}
	
	public int insertCart(Cart cart) {
		return dao.insertCart(cart);
	}
	
	public ArrayList<Cart> listCart(Member memberId){
		return dao.listCart(memberId);
	}
	
	public int deleteCart(int no) {
		return dao.deleteCart(no);
	}

}
