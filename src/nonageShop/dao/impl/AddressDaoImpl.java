package nonageShop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageShop.dao.AddressDao;
import nonageShop.dto.Address;

public class AddressDaoImpl implements AddressDao {
	private static final AddressDaoImpl instance = new AddressDaoImpl();
	private Connection con;

	public AddressDaoImpl() {
	}

	public static AddressDaoImpl getInstance() {
		return instance;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public ArrayList<Address> selectAddressByDong(String dong) {
		String sql = "SELECT ZIP_NUM, SIDO, GUGUN, DONG, ZIP_CODE, BUNJI FROM ADDRESS WHERE DONG LIKE '%' || ? || '%'";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, dong);
			
			try (ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					ArrayList<Address> list = new ArrayList<Address>();
					do {
						list.add(getAddress(rs));
					} while(rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return null;
	}

	private Address getAddress(ResultSet rs) throws SQLException {
		Address address = new Address();
		
		address.setZipNum(rs.getString("ZIP_NUM"));
		address.setSido(rs.getString("SIDO"));
		address.setGugun(rs.getString("GUGUN"));
		address.setDong(rs.getString("DONG"));
		address.setZipCode(rs.getString("ZIP_CODE"));
		address.setBunji(rs.getString("BUNJI"));
		
		
		return address;
	}

}
