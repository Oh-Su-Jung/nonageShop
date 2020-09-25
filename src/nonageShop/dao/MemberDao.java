package nonageShop.dao;

import nonageShop.dto.Member;

public interface MemberDao {
	int confirmID(String id);
	
	Member getMember(String id);
	
	int insertMember(Member member);
}
