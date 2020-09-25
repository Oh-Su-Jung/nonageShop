package nonageShop.dao;

import java.sql.Connection;

import org.junit.BeforeClass;
import org.junit.Test;

import nonageShop.dao.impl.WorkerDaoImpl;
import nonageShop.ds.JdbcUtil;

public class WorkerDaoTest {
	private static Connection con;
	private static WorkerDaoImpl dao;
	
	@BeforeClass
	public static void setupBeforeClass() throws Exception {
		con = JdbcUtil.getConnection();
		dao = WorkerDaoImpl.getInstance();
		dao.setCon(con);
	}

	@Test
	public void testWorkerCheck() {
		System.out.println("testWorkerCheck()");
		int result = dao.workerCheck("admin0", "admin");
		System.out.println(result);
	}

}
