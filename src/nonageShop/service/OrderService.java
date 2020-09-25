package nonageShop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageShop.dao.impl.CartDaoImpl;
import nonageShop.dao.impl.OrderDaoImpl;
import nonageShop.ds.JdbcUtil;
import nonageShop.ds.JndiDS;
import nonageShop.dto.Member;
import nonageShop.dto.OrderDetail;
import nonageShop.dto.Orders;

public class OrderService {
	private OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
	private CartDaoImpl cartDao = CartDaoImpl.getInstance();

	public OrderService() {
		orderDao.setCon(JdbcUtil.getConnection());
		cartDao.setCon(JdbcUtil.getConnection());
	}
	
	public ArrayList<Integer> selectSeqOrderIng(Member member){
		return orderDao.selectSeqOrderIng(member);
	}
	
	public Orders orderListByMember(String memberId, int orderNo, String result) {
		return orderDao.listOrderByMember(memberId, orderNo, result);
	}
	
	public int maxOrderNo() {
		return orderDao.selectMaxOrdersNo();
	}

	public int addOrderAndDetail(Orders orders) {
		String orderSql = "INSERT INTO ORDERS(ID) VALUES(?)";
		String detailSql = "INSERT INTO ORDER_DETAIL(ONO, PNO, QUANTITY) VALUES(?, ?, ?)";
		Connection con = null;
		PreparedStatement orderPstmt = null;
		PreparedStatement detailPstmt = null;
		int orderMaxNo = 0;
		try {
			con = JndiDS.getConnection();
			con.setAutoCommit(false);
			
			orderPstmt = con.prepareStatement(orderSql);
			orderPstmt.setString(1, orders.getMember().getId());
			orderPstmt.executeUpdate();

			detailPstmt = con.prepareStatement(detailSql);
			orderMaxNo = orderDao.selectMaxOrdersNo()+1;
			System.out.println(detailPstmt);
			System.out.println(orderMaxNo);
			
			for (OrderDetail od : orders.getDetails()) {
				detailPstmt.setInt(1, orderMaxNo);
				detailPstmt.setInt(2, od.getCart().getPno().getNo());
				detailPstmt.setInt(3, od.getCart().getQuantity());
				detailPstmt.executeUpdate();
				
				cartDao.updateCartResult(od.getCart());
			}

			con.commit();
		} catch (SQLException e) {
			rollbackUtil(con, e);
		} finally {
			closeUtil(con, orderPstmt, detailPstmt);
		}
		return orderMaxNo;
	}

	private void rollbackUtil(Connection con, SQLException e) {
		try {
			System.out.println("roll back");
			con.rollback();
			throw new RuntimeException();
		} catch (SQLException ex) {
			
		}
	}

	private void closeUtil(Connection con, PreparedStatement orderPstmt, PreparedStatement detailPstmt) {
		try {
			if (detailPstmt != null) {
				detailPstmt.close();
			}
			if (orderPstmt != null) {
				orderPstmt.close();
			}
			if (con != null) {
				con.setAutoCommit(true);
				con.close();
			}
		} catch (SQLException ex) {
			
		}
	}
}
