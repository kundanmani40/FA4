package com.user.dto;

//ProductDTO class that tells about instances(properties) for 'Product'
public class ProductDTO {

	//Instances of 'Product'
	private String prodId;
	private String productName;
	private float price;
	private Integer stock;
	private String description;
	private String image;
	private String category;
	private String sellerId;
	private String subCategory;
	private float productRating;
	
	//Getters and Setters methods for above instances
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public float getProductRating() {
		return productRating;
	}
	public void setProductRating(float productRating) {
		this.productRating = productRating;
	}
	
	//toString() method to get proper println during testing
	@Override
	public String toString() {
		return "ProductDTO [prodId=" + prodId + ", productName=" + productName + ", price=" + price + ", stock=" + stock
				+ ", description=" + description + ", image=" + image + ", category=" + category + ", sellerId="
				+ sellerId + ", subCategory=" + subCategory + ", productRating=" + productRating + "]";
	}
	
}
