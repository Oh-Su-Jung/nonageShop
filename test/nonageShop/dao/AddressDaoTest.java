package nonageShop.dao;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nonageShop.dao.impl.AddressDaoImpl;
import nonageShop.ds.JdbcUtil;
import nonageShop.dto.Address;

public class AddressDaoTest {
	private static Connection con;
	private static AddressDaoImpl dao;
	
	@BeforeClass
	public static void setupBeforeClass() throws Exception {
		con = JdbcUtil.getConnection();
		dao = AddressDaoImpl.getInstance();
		dao.setCon(con);
	}

	@Test
	public void testSelectAddressByDong() {
		System.out.println("testSelectAddressByDong()");
		ArrayList<Address> address = dao.selectAddressByDong("µ¿Ãµµ¿");
		Assert.assertNotNull(address);
		address.stream().forEach(System.out::println);
	}

}
