package com.product.dto;

//SubscribedProductDTO class that tells about instances(properties) for 'Subscribed Product'
public class SubscribedProductDTO {
	
	//Instances of 'Subscribed Product'
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
		return "SubscribedProductDTO [buyerId=" + buyerId + ", prodId=" + prodId + ", quantity=" + quantity + "]";
	}
	
	
	
}
