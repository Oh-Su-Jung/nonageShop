package nonageShop.dto;

import java.util.Date;

public class Cart {
	private int no;
	private Product pno;
	private Member memberId;
	private int quantity;
	private String resultYn;
	private Date regDate;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Product getPno() {
		return pno;
	}

	public void setPno(Product pno) {
		this.pno = pno;
	}

	public Member getMemberId() {
		return memberId;
	}

	public void setMemberId(Member memberId) {
		this.memberId = memberId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getResultYn() {
		return resultYn;
	}

	public void setResultYn(String resultYn) {
		this.resultYn = resultYn;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return String.format("Cart [no=%s, pno=%s, memberId=%s, quantity=%s, resultYn=%s, regDate=%s]", no, pno,
				memberId, quantity, resultYn, regDate);
	}
}
