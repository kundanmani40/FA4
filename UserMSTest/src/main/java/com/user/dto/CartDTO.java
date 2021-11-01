package com.user.dto;

//CartDTO class that tells about instances(properties) for 'Cart'
public class CartDTO {

	//Instances of 'Cart'
	private String buyerId;
	private String prodId;
	private Integer quantity;
	
	//Getters and Setters methods for above instances
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	//toString() method to get proper println during testing
	@Override
	public String toString() {
		return "CartDTO [buyerId=" + buyerId + ", prodId=" + prodId + ", quantity=" + quantity + "]";
	}
	
}
