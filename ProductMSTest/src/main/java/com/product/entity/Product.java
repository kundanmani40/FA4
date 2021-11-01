package com.product.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Stating that this is an Entity class using @Entity and giving table name
@Entity
@Table(name = "product")
public class Product {

	//Specifying primary key and all instances for 'Product'
	@Id
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
	
	//Getter and Setter methods for above instances
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
		return "Product [prodId=" + prodId + ", productName=" + productName + ", price=" + price + ", stock=" + stock
				+ ", description=" + description + ", image=" + image + ", category=" + category + ", sellerId="
				+ sellerId + ", subCategory=" + subCategory + ", productRating=" + productRating + "]";
	}
	
	//If two objects are equal according to equals(...) then hashCode() returns same value
	@Override
	public int hashCode() {
		return Objects.hash(category, description, image, price, prodId, productName, productRating, sellerId, stock,
				subCategory);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(category, other.category) && Objects.equals(description, other.description)
				&& Objects.equals(image, other.image)
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price)
				&& Objects.equals(prodId, other.prodId) && Objects.equals(productName, other.productName)
				&& Float.floatToIntBits(productRating) == Float.floatToIntBits(other.productRating)
				&& Objects.equals(sellerId, other.sellerId) && Objects.equals(stock, other.stock)
				&& Objects.equals(subCategory, other.subCategory);
	}
	
	
	
}
