package nonageShop.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nonageShop.dao.impl.CartDaoImpl;
import nonageShop.ds.JdbcUtil;
import nonageShop.dto.Cart;
import nonageShop.dto.Member;
import nonageShop.dto.Product;

public class CartDaoTest {
	private static Connection con;
	private static CartDaoImpl dao;
	
	@BeforeClass
	public static void setupBeforeClass() throws Exception {
		con = JdbcUtil.getConnection();
		dao = CartDaoImpl.getInstance();
		dao.setCon(con);
	}

	@Test
	public void testInsertCart() {
		System.out.println("testInsertCart()");
		Cart cart = new Cart();
		cart.setPno(new Product(2));
		cart.setMemberId(new Member("test"));
		cart.setQuantity(3);
		
		int res = dao.insertCart(cart);
		Assert.assertNotEquals(1, res);
	}

	@Test
	public void testListCart() {
		System.out.println("testListCart()");
		ArrayList<Cart> cartList = dao.listCart(new Member("one"));
		Assert.assertNotNull(cartList);
		cartList.stream().forEach(System.out::println);
	}

	@Test
	public void testDeleteCart() {
		System.out.println("testDeleteCart()");
		int res = dao.deleteCart(4);
		Assert.assertNotEquals(1, res);
		
	}

}
