package com.product.entity;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.product.utility.MyPrimaryKey;

//Stating that this is an Entity class using @Entity and giving table name
@Entity
@Table(name = "subscribed_product")
public class SubscribedProduct {
	
	//Specifying primary key and all instances for 'SubscribedProduct'. Here, we are using composite key so @EmbeddedId
	@EmbeddedId
	private MyPrimaryKey myPK;
	private Integer quantity;

	//Getter and Setter methods for above instances
	public MyPrimaryKey getCompositepk() {
		return myPK;
	}

	public void setCompositepk(MyPrimaryKey compositepk) {
		this.myPK = compositepk;
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
		return "SubscribedProduct [myPK=" + myPK + ", quantity=" + quantity + "]";
	}

	//If two objects are equal according to equals(...) then hashCode() returns same value
	@Override
	public int hashCode() {
		return Objects.hash(myPK, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubscribedProduct other = (SubscribedProduct) obj;
		return Objects.equals(myPK, other.myPK) && Objects.equals(quantity, other.quantity);
	}	
	
	

}
