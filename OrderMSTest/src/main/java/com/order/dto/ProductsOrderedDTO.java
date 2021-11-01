package com.order.dto;

//ProductsOrderedDTO class that tells about instances(properties) for 'ProductsOrdered'
public class ProductsOrderedDTO {
	
	//Instances of 'ProductsOrdered'
	private String buyerId;
	private String sellerId;
	private String prodId;
	private Integer quantity;
	
	//Getters and Setters methods for above instances
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
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
		return "ProductsOrderedDTO [buyerId=" + buyerId + ", sellerId=" + sellerId + ", prodId=" + prodId
				+ ", quantity=" + quantity + "]";
	}	

}
