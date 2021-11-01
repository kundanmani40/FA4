package com.order.entity;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.order.utility.MyPrimaryKey;

//Stating that this is an Entity class using @Entity and giving table name
@Entity
@Table(name = "products_ordered")
public class ProductsOrdered {

	//Specifying primary key and all instances for 'ProductsOrdered'. Here, we are using composite key so @EmbeddedId
	@EmbeddedId
	private MyPrimaryKey myPrimaryKey;
	private String sellerId;
	private Integer quantity;
	
	//Getter and Setter methods for above instances
	public MyPrimaryKey getMyPrimaryKey() {
		return myPrimaryKey;
	}
	public void setMyPrimaryKey(MyPrimaryKey myPrimaryKey) {
		this.myPrimaryKey = myPrimaryKey;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
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
		return "ProductsOrdered [myPrimaryKey=" + myPrimaryKey + ", sellerId=" + sellerId + ", quantity=" + quantity
				+ "]";
	}
	
	//If two objects are equal according to equals(...) then hashCode() returns same value
	@Override
	public int hashCode() {
		return Objects.hash(myPrimaryKey, quantity, sellerId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductsOrdered other = (ProductsOrdered) obj;
		return Objects.equals(myPrimaryKey, other.myPrimaryKey) && Objects.equals(quantity, other.quantity)
				&& Objects.equals(sellerId, other.sellerId);
	}	
	
}
