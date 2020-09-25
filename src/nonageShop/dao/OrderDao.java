package nonageShop.dao;

import java.util.ArrayList;

import nonageShop.dto.Member;
import nonageShop.dto.Orders;

public interface OrderDao {
	
	int selectMaxOrdersNo();
	
	Orders listOrderByMember(String memberId, int orderNo, String result);
	
	ArrayList<Integer> selectSeqOrderIng(Member member);
	
	
}
