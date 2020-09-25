package nonageShop.service;

import java.sql.Connection;

import nonageShop.dao.impl.QnaDaoImpl;
import nonageShop.dao.impl.WorkerDaoImpl;
import nonageShop.ds.JndiDS;

public class WorkerService {
	private WorkerDaoImpl dao = WorkerDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public WorkerService() {
		dao.setCon(con);
	}
	
	public int workerCheck(String id, String pwd) {
		return dao.workerCheck(id, pwd);
	}

}
