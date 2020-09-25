package nonageShop.service;

import java.sql.Connection;
import java.util.ArrayList;

import nonageShop.dao.impl.QnaDaoImpl;
import nonageShop.ds.JndiDS;
import nonageShop.dto.Qna;

public class QnaService {
	private QnaDaoImpl dao = QnaDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public QnaService() {
		dao.setCon(con);
	}
	
	public ArrayList<Qna> listQna(String id) {
		return dao.listQna(id);
	}
	
	public Qna getQna(int no) {
		return dao.getQna(no);
	}
	
	public int insertQna(Qna qna) {
		return dao.insertQna(qna);
	}

}
