package nonageShop.dao;

import java.util.ArrayList;

import nonageShop.dto.Qna;

public interface QnaDao {
	ArrayList<Qna> listQna(String id);
	
	Qna getQna(int no);
	
	int insertQna(Qna qna);
}
