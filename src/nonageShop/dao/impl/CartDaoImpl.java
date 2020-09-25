package nonageShop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageShop.dao.CartDao;
import nonageShop.dto.Cart;
import nonageShop.dto.Member;
import nonageShop.dto.Product;

public class CartDaoImpl implements CartDao {
	private static final CartDaoImpl instance = new CartDaoImpl();
	private Connection con;

	public CartDaoImpl() {
	}

	public static CartDaoImpl getInstance() {
		return instance;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public int insertCart(Cart cart) {
		String sql = "INSERT INTO CART(PNO, MEMBER_ID, QUANTITY) values(?, ?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, cart.getPno().getNo());
			pstmt.setString(2, cart.getMemberId().getId());
			pstmt.setInt(3, cart.getQuantity());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public ArrayList<Cart> listCart(Member memberId) {
		String sql = "SELECT NO, MEMBER_ID, PNO, MNAME, PNAME, QUANTITY, REG_DATE, SALE_PRICE, RESULT_YN FROM CART_VIEW WHERE MEMBER_ID = ? ORDER BY NO";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, memberId.getId());
			
			try (ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					ArrayList<Cart> list = new ArrayList<>();
					do {
						list.add(getCart(rs));
					} while (rs.next());
					return list;
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return null;
	}

	private Cart getCart(ResultSet rs) throws SQLException {
		Cart cart = new Cart();
		Product product = new Product();
		Member member = new Member();
		
		product.setNo(rs.getInt("PNO"));
		product.setName(rs.getString("PNAME"));
		product.setSalePrice(rs.getInt("SALE_PRICE"));
		
		member.setId(rs.getString("MEMBER_ID"));
		member.setName(rs.getString("MNAME"));
		
		cart.setNo(rs.getInt("NO"));
		cart.setPno(product);
		cart.setMemberId(member);
		cart.setQuantity(rs.getInt("QUANTITY"));
		cart.setResultYn(rs.getString("RESULT_YN"));
		cart.setRegDate(rs.getTimestamp("REG_DATE"));
		
		return cart;
	}

	@Override
	public int deleteCart(int no) {
		String sql = "DELETE cart WHERE NO = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, no);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public int updateCartResult(Cart cart) {
		String sql = "UPDATE CART SET RESULT_YN = 2 WHERE NO = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, cart.getNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
