package nonageShop.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nonageShop.dao.impl.OrderDaoImpl;
import nonageShop.ds.JdbcUtil;
import nonageShop.dto.Orders;

public class OrderDaoTest {
	private static Connection con;
	private static OrderDaoImpl dao;
	
	@BeforeClass
	public static void setupBeforeClass() throws Exception {
		con = JdbcUtil.getConnection();
		dao = OrderDaoImpl.getInstance();
		dao.setCon(con);
	}

	@Test
	public void testSelectMaxOrdersNo() {
		System.out.println("testSelectMaxOrdersNo()");
		int res = dao.selectMaxOrdersNo();
		Assert.assertNotEquals(1, res);
	}

	@Test
	public void testListOrderByMember() {
		System.out.println("testListOrderByMember()");
		Orders orders = dao.listOrderByMember("one", 1, "1");
		Assert.assertNotNull(orders);
		System.out.println(orders);
	}

	@Test
	public void testSelectSeqOrderIng() {
		fail("Not yet implemented");
	}

}
