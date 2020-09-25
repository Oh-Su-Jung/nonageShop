package nonageShop.dto;

import java.util.Date;

public class OrderDetail {
	private int no;
	private Cart cart;
	private String reault; // 1:固贸府 2:贸府
	private Date orderDate;

	public OrderDetail() {
		// TODO Auto-generated constructor stub
	}

	public OrderDetail(Cart cart) {
		this.cart = cart;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String getReault() {
		return reault;
	}

	public void setReault(String reault) {
		this.reault = reault;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return String.format("OrderDetail [no=%s, cart=%s, reault=%s, orderDate=%s]", no, cart, reault, orderDate);
	}
}
