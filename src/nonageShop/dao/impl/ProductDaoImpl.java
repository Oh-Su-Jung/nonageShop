package nonageShop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageShop.dao.ProductDao;
import nonageShop.dto.Product;

public class ProductDaoImpl implements ProductDao {
	private static final ProductDaoImpl instance = new ProductDaoImpl();
	private Connection con;

	private ProductDaoImpl() {
	}

	public static ProductDaoImpl getInstance() {
		return instance;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public ArrayList<Product> listNewProduct() {
		String sql = "SELECT NO, NAME, SALE_PRICE, IMAGE FROM NEW_PRO_VIEW";
		try (PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if (rs.next()) {
				ArrayList<Product> list = new ArrayList<Product>();
				do {
					list.add(getNewBestProduct(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return null;
	}

	@Override
	public ArrayList<Product> listBestProduct() {
		String sql = "SELECT NO, NAME, SALE_PRICE, IMAGE FROM BEST_PRO_VIEW";
		try (PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if (rs.next()) {
				ArrayList<Product> list = new ArrayList<Product>();
				do {
					list.add(getNewBestProduct(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return null;
	}

	private Product getNewBestProduct(ResultSet rs) throws SQLException {
		int no = rs.getInt("NO");
		String name = rs.getString("NAME");
		int salePrice = rs.getInt("SALE_PRICE");
		String image = rs.getString("IMAGE");
		return new Product(no, name, salePrice, image);
	}

	@Override
	public Product getProduct(int no) {
		String sql = "SELECT NO, NAME, KIND, PRICE, SALE_PRICE, MARGIN, CONTENT, IMAGE, DEL_YN, BEST_YN, REG_DATE FROM PRODUCT WHERE NO = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, no);
			
			try (ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					return getProduct(rs);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return null;
	}

	@Override
	public ArrayList<Product> listKindProduct(String kind) {
		String sql = "SELECT NO, NAME, KIND, PRICE, SALE_PRICE, MARGIN, CONTENT, IMAGE, DEL_YN, BEST_YN, REG_DATE FROM PRODUCT WHERE KIND = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, kind);
			
			try (ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					ArrayList<Product> list = new ArrayList<Product>();
					do {						
						list.add(getProduct(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return null;
	}

	private Product getProduct(ResultSet rs) throws SQLException {
		Product product = new Product();
		
		product.setNo(rs.getInt("NO"));
		product.setName(rs.getString("NAME"));
		product.setKind(rs.getString("KIND"));
		product.setPrice(rs.getInt("PRICE"));
		product.setSalePrice(rs.getInt("SALE_PRICE"));
		product.setMargin(rs.getInt("MARGIN"));
		product.setContent(rs.getString("CONTENT"));
		product.setImage(rs.getString("IMAGE"));
		product.setDelYn(rs.getString("DEL_YN"));
		product.setBestYn(rs.getString("BEST_YN"));
		product.setRegDate(rs.getTimestamp("REG_DATE"));
		
		return product;
	}

	@Override
	public int totalRecord(String productName) {
		int total_pages = 0;
		String sql = "SELECT COUNT(*) FROM PRODUCT WHERE NAME LIKE ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			if (productName.equals("")) {
				pstmt.setString(1, "%%");
			} else {
				pstmt.setString(1,  "%" + productName + "%");
			}
			try (ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					total_pages = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return total_pages;
	}
	
	private static int VIEW_ROWS = 5;
	private static int COUNTS = 5;

	@Override
	public String pageNumber(int tpage, String name) {
		return null;
	}

	@Override
	public ArrayList<Product> listProduct(int tpage, String productName) {
		return null;
	}

	@Override
	public int insertProduct(Product producdt) {
		return 0;
	}

	@Override
	public int updateProduct(Product product) {
		return 0;
	}

}
