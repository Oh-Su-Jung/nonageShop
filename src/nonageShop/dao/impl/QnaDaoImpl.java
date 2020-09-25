package nonageShop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageShop.dao.QnaDao;
import nonageShop.dto.Qna;

public class QnaDaoImpl implements QnaDao {
	private static final QnaDaoImpl instance = new QnaDaoImpl();
	private Connection con;

	private QnaDaoImpl() {
	}

	public static QnaDaoImpl getInstance() {
		return instance;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public ArrayList<Qna> listQna(String id) {
		String sql = "SELECT NO, SUBJECT, CONTENT, REP, ID, REP_YN, WRITER_DATE FROM QNA WHERE ID = ? ORDER BY NO DESC";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					ArrayList<Qna> list = new ArrayList<>();
					do {
						list.add(getQna(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return null;
	}

	private Qna getQna(ResultSet rs) throws SQLException {
		Qna qna = new Qna();
		qna.setNo(rs.getInt("NO"));
		qna.setSubject(rs.getString("SUBJECT"));
		qna.setContent(rs.getString("CONTENT"));
		qna.setRep(rs.getString("REP"));
		qna.setId(rs.getString("ID"));
		qna.setRepYn(rs.getString("REP_YN"));
		qna.setWriterDate(rs.getTimestamp("WRITER_DATE"));
		return qna;
	}

	@Override
	public Qna getQna(int no) {
		String sql = "SELECT NO, SUBJECT, CONTENT, REP, ID, REP_YN, WRITER_DATE FROM QNA WHERE NO = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, no);
			
			try (ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					return getQna(rs);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return null;
	}

	@Override
	public int insertQna(Qna qna) {
		String sql = "INSERT INTO QNA (SUBJECT, CONTENT, ID) VALUES(?, ?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, qna.getSubject());
			pstmt.setString(2, qna.getContent());
			pstmt.setString(3, qna.getId());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
