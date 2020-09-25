package nonageShop.dto;

import java.util.ArrayList;
import java.util.Date;

public class Orders {
	private int no;
	private Member member;
	private Date orderDate;
	private ArrayList<OrderDetail> details;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public ArrayList<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(ArrayList<OrderDetail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return String.format("Orders [no=%s, member=%s, orderDate=%s, details=%s]", no, member, orderDate, details);
	}
}
