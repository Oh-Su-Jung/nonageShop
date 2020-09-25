package nonageShop.service;

import java.sql.Connection;

import nonageShop.dao.impl.MemberDaoImpl;
import nonageShop.ds.JndiDS;
import nonageShop.dto.Member;

public class MemberService {
	private MemberDaoImpl dao = MemberDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public MemberService() {
		dao.setCon(con);
	}
	
	public int confirmID(String id) {
		return dao.confirmID(id);
	}
	
	public int insertMember(Member member) {
		return dao.insertMember(member);
	}
	
	public Member getMember(String id) {
		return dao.getMember(id);
	}

}
