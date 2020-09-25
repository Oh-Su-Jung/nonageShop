package nonageShop.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nonageShop.dao.impl.ProductDaoImpl;
import nonageShop.ds.JdbcUtil;
import nonageShop.dto.Product;

public class ProductDaoTest {
	private static Connection con;
	private static ProductDaoImpl dao;
	
	@BeforeClass
	public static void setupBeforeClass() throws Exception {
		con = JdbcUtil.getConnection();
		dao = ProductDaoImpl.getInstance();
		dao.setCon(con);
	}

	@Test
	public void testListNewProduct() {
		System.out.println("testListNewProduct()");
		ArrayList<Product> list = dao.listNewProduct();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void testListBestProduct() {
		System.out.println("testListBestProduct()");
		ArrayList<Product> list = dao.listBestProduct();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void testGetProduct() {
		System.out.println("testGetProduct()");
		Product product = dao.getProduct(3);
		System.out.println(product);
	}

	@Test
	public void testListKindProduct() {
		System.out.println("testListKindProduct()");
		String[] kindArr = {"1", "2", "3", "4", "5"};
		for (String kind : kindArr) {
			ArrayList<Product> kindList = dao.listKindProduct(kind);
			Assert.assertNotNull(kindList);
			System.out.println(kind);
			kindList.stream().forEach(System.out::println);
		}
		
		/*ArrayList<Product> list = dao.listKindProduct("2");
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);*/
	}

}
