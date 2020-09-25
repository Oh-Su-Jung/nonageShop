package nonageShop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageShop.dao.OrderDao;
import nonageShop.dto.Cart;
import nonageShop.dto.Member;
import nonageShop.dto.OrderDetail;
import nonageShop.dto.Orders;
import nonageShop.dto.Product;

public class OrderDaoImpl implements OrderDao {
	private static final OrderDaoImpl instance = new OrderDaoImpl();
	private Connection con;

	public OrderDaoImpl() {
	}

	public static OrderDaoImpl getInstance() {
		return instance;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public int selectMaxOrdersNo() {
		String sql = "SELECT MAX(NO) FROM ORDERS";
		try (PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return 0;
	}

	@Override
	public Orders listOrderByMember(String memberId, int orderNo, String result) {
		String sql = "SELECT ONO, MID, MNAME, PHONE, ZIP_NUM, ADDRESS, DNO, ORDER_DATE, RESULT, PNO, PNAME, QUANTITY, SALE_PRICE  " + 
				"  FROM ORDER_VIEW  " + 
				" WHERE MID = ? AND RESULT LIKE ? AND ONO = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			System.out.println(1);
			pstmt.setString(1, memberId);
			pstmt.setString(2, "%"+ result +"%");
			pstmt.setInt(3, orderNo);
			
			try (ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					System.out.println(2);
					Orders orders = new Orders();
					System.out.println(3);
					orders.setNo(rs.getInt("ONO"));
	                Member member = new Member(rs.getString("MID"), rs.getString("MNAME"));
					System.out.println(4);
	                member.setPhone(rs.getString("PHONE"));
	                member.setZipNum(rs.getString("ZIP_NUM"));
	                member.setAddress(rs.getString("ADDRESS"));
	                orders.setMember(member);
	                
	                ArrayList<OrderDetail> details = new ArrayList<OrderDetail>();
	                do {
	                    details.add(getOrderDetail(rs));
	                }while(rs.next());
	                
	                orders.setDetails(details);
	                orders.setOrderDate(details.get(0).getOrderDate());
	                return orders;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return null;
	}

	private OrderDetail getOrderDetail(ResultSet rs) throws SQLException {
		OrderDetail detail = new OrderDetail();
		detail.setNo(rs.getInt("DNO"));
		detail.setOrderDate(rs.getTimestamp("ORDER_DATE"));
		detail.setReault(rs.getString("RESULT"));
		
		Product product = new Product(rs.getInt("PNO"), rs.getString("PNAME"));
		product.setSalePrice(rs.getInt("SALE_PRICE"));
		
		Cart cart = new Cart();
		cart.setPno(product);
		cart.setQuantity(rs.getInt("QUANTITY"));
		
		detail.setCart(cart);
		return detail;
	}

	@Override
	public ArrayList<Integer> selectSeqOrderIng(Member member) {
		String sql = "SELECT DISTINCT ONO FROM ORDER_VIEW WHERE MID = ? AND RESULT = '1' ORDER BY ONO DESC";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, member.getId());
			try (ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					ArrayList<Integer> orderNo = new ArrayList<Integer>();
					do {
						orderNo.add(rs.getInt(1));
					} while (rs.next());
					return orderNo;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return null;
	}

}
