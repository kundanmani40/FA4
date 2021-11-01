package com.order.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Stating that this is an Entity class using @Entity and giving table name
@Entity
@Table(name = "order_table")
public class Order {

	//Specifying primary key and all instances for 'Order'
	@Id
	private String orderId;
	private String buyerId;
	private float amount;
	private LocalDate date;
	private String address;
	private String status;
	
	//Getter and Setter methods for above instances
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	//toString() method to get proper println during testing
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", buyerId=" + buyerId + ", amount=" + amount + ", date=" + date
				+ ", address=" + address + ", status=" + status + "]";
	}
	
	//If two objects are equal according to equals(...) then hashCode() returns same value
	@Override
	public int hashCode() {
		return Objects.hash(address, amount, buyerId, date, orderId, status);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(address, other.address)
				&& Float.floatToIntBits(amount) == Float.floatToIntBits(other.amount)
				&& Objects.equals(buyerId, other.buyerId) && Objects.equals(date, other.date)
				&& Objects.equals(orderId, other.orderId) && Objects.equals(status, other.status);
	}
	
	
	
}
