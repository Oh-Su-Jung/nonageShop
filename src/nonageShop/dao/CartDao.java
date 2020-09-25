package nonageShop.dao;

import java.util.ArrayList;

import nonageShop.dto.Cart;
import nonageShop.dto.Member;

public interface CartDao {
	int insertCart(Cart cart);
	
	ArrayList<Cart> listCart(Member memberId);
	
	int deleteCart(int no);
	
	int updateCartResult(Cart cart);
}
