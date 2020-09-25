package nonageShop.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nonageShop.dao.impl.QnaDaoImpl;
import nonageShop.ds.JdbcUtil;
import nonageShop.dto.Qna;

public class QnaDaoTest {
	private static Connection con;
	private static QnaDaoImpl dao;
	
	@BeforeClass
	public static void setupBeforeClass() throws Exception {
		con = JdbcUtil.getConnection();
		dao = QnaDaoImpl.getInstance();
		dao.setCon(con);
	}

	@Test
	public void testListQna() {
		System.out.println("testListQna()");
		ArrayList<Qna> list = dao.listQna("one");
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void testGetQna() {
		System.out.println("testGetQna()");
		Qna qna = dao.getQna(1);
		Assert.assertNotNull(qna);
		System.out.println(qna);
	}

	@Test
	public void testInsertQna() {
		System.out.println("testInsertQna()");
		Qna qna = new Qna();
		qna.setSubject("테스트3");
		qna.setContent("질문내용3");
		qna.setId("test");
		int res = dao.insertQna(qna);
		Assert.assertNotEquals(1, res);
	}

}
