package com.order.utility;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

//Embeddable class for our custom primary key
@SuppressWarnings("serial")
@Embeddable
public class MyPrimaryKey implements Serializable {
	
	protected String buyerId;
	protected String prodId;
	
	//Constructors
	public MyPrimaryKey()
	{
		
	}
	
	public MyPrimaryKey(String buyerId, String prodId)
	{
		super();
		this.buyerId=buyerId;
		this.prodId=prodId;
	}

	//Getters and Setters for above instances
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

	//toString() method for printing during testing
	@Override
	public String toString() {
		return "MyPrimaryKey [buyerId=" + buyerId + ", prodId=" + prodId + "]";
	}

	//hashCode() and equals() methods
	@Override
	public int hashCode() {
		return Objects.hash(buyerId, prodId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyPrimaryKey other = (MyPrimaryKey) obj;
		return Objects.equals(buyerId, other.buyerId) && Objects.equals(prodId, other.prodId);
	}

}
