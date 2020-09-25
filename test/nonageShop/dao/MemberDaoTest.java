package nonageShop.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nonageShop.dao.impl.MemberDaoImpl;
import nonageShop.ds.JdbcUtil;
import nonageShop.dto.Member;

public class MemberDaoTest {
	private static Connection con;
	private static MemberDaoImpl dao;
	
	@BeforeClass
	public static void setupBeforeClass() throws Exception {
		con = JdbcUtil.getConnection();
		dao = MemberDaoImpl.getInstance();
		dao.setCon(con);
	}

	@Test
	public void testConfirmID() {
		System.out.println("testConfirmID()");
		int res = dao.confirmID("one");
		Assert.assertNotEquals(1, res);
	}

	@Test
	public void testGetMember() {
		System.out.println("testGetMember()");
		Member member = dao.getMember("one");
		Assert.assertNotNull(member);
		System.out.println(member);
	}

	@Test
	public void testInsertMember() {
		System.out.println("testInsertMember()");
		Member newMember = new Member();
		newMember.setId("three");
		newMember.setPwd("3333");
		newMember.setName("홍길동");
		newMember.setEmail("test@test.com");
		newMember.setZipNum("123-111");
		newMember.setAddress("대구광역시 서구 내당동");
		newMember.setPhone("010-1111-2222");
		
		int res = dao.insertMember(newMember);
		Assert.assertNotEquals(1, res);
	}

}
