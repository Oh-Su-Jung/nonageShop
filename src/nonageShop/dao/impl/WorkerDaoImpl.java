package nonageShop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nonageShop.dao.WorkerDao;

public class WorkerDaoImpl implements WorkerDao {
	private static final WorkerDaoImpl instance = new WorkerDaoImpl();
	private Connection con;

	private WorkerDaoImpl() {
	}

	public static WorkerDaoImpl getInstance() {
		return instance;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public int workerCheck(String id, String pwd) {
		String sql = "SELECT PWD FROM WORKER WHERE ID = ?";
		int result = -1;
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, id);
			
			try (ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					result = 0;
					String dbPwd = rs.getString(1);
					if (dbPwd.equals(pwd)) {
						result = 1;
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return result;
	}

}
