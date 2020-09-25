package nonageShop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nonageShop.dao.MemberDao;
import nonageShop.dto.Member;

public class MemberDaoImpl implements MemberDao {
	private static final MemberDaoImpl instance = new MemberDaoImpl();
	private Connection con;

	public MemberDaoImpl() {
	}

	public static MemberDaoImpl getInstance() {
		return instance;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public int confirmID(String id) {
		String sql = "SELECT ID, PWD, NAME, EMAIL, ZIP_NUM, ADDRESS, PHONE, LEAVE_YN, JOIN_DATE FROM MEMBER WHERE ID = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()){
				int result = -1;
				if (rs.next()) {
					result = 1;
				} else {
					result = -1;
				}
				return result;
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public Member getMember(String id) {
		String sql = "SELECT ID, PWD, NAME, EMAIL, ZIP_NUM, ADDRESS, PHONE, LEAVE_YN, JOIN_DATE FROM MEMBER WHERE ID = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, id);
			
			try (ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					return getMember(rs);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return null;
	}

	private Member getMember(ResultSet rs) throws SQLException {
		Member member = new Member();
		
		member.setId(rs.getString("ID"));
		member.setPwd(rs.getString("PWD"));
		member.setName(rs.getString("NAME"));
		member.setEmail(rs.getString("EMAIL"));
		member.setZipNum(rs.getString("ZIP_NUM"));
		member.setAddress(rs.getString("ADDRESS"));
		member.setPhone(rs.getString("PHONE"));
		member.setLeaveYn(rs.getString("LEAVE_YN"));
		member.setJoinDate(rs.getTimestamp("JOIN_DATE"));
		
		return member;
	}

	@Override
	public int insertMember(Member member) {
		String sql = "INSERT INTO MEMBER(ID, PWD, NAME, EMAIL, ZIP_NUM, ADDRESS, PHONE) VALUES(?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getZipNum());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getPhone());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
