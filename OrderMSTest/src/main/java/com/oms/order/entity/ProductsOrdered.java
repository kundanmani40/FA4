package com.oms.order.entity;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.oms.order.utility.CustomPK;

@Entity
@Table(name = "products_ordered")
public class ProductsOrdered {
	
	@EmbeddedId
	private CustomPK primaryKeys;
	
	private String sellerId;	
	private Integer quantity;
	public CustomPK getPrimaryKeys() {
		return primaryKeys;
	}
	public void setPrimaryKeys(CustomPK primaryKeys) {
		this.primaryKeys = primaryKeys;
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
	@Override
	public String toString() {
		return "ProductsOrdered [primaryKeys=" + primaryKeys + ", sellerId=" + sellerId + ", quantity=" + quantity
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(primaryKeys, quantity, sellerId);
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
		return Objects.equals(primaryKeys, other.primaryKeys) && Objects.equals(quantity, other.quantity)
				&& Objects.equals(sellerId, other.sellerId);
	}
	
	
}
