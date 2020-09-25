package nonageShop.dto;

import java.util.Date;

public class Qna {
	private int no;
	private String subject;
	private String content;
	private String rep;
	private String id;
	private String repYn;
	private Date writerDate;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRep() {
		return rep;
	}

	public void setRep(String rep) {
		this.rep = rep;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRepYn() {
		return repYn;
	}

	public void setRepYn(String repYn) {
		this.repYn = repYn;
	}

	public Date getWriterDate() {
		return writerDate;
	}

	public void setWriterDate(Date writerDate) {
		this.writerDate = writerDate;
	}

	@Override
	public String toString() {
		return String.format("Qna [no=%s, subject=%s, content=%s, rep=%s, id=%s, repYn=%s, writerDate=%s]", no, subject,
				content, rep, id, repYn, writerDate);
	}
}
