package com.user.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Stating that this is an Entity class using @Entity and giving table name
@Entity
@Table(name = "buyer")
public class Buyer {
	
	//Specifying primary key and all instances for 'Buyer'
	@Id
	private String buyerId;
	private String name;
	private String email;
	private String phoneNumber;
	private String password;
	private String isPrivileged;
	private String rewardPoints;
	private String isActive;
	
	//Getter and Setter methods for above instances
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsPrivileged() {
		return isPrivileged;
	}
	public void setIsPrivileged(String isPrivileged) {
		this.isPrivileged = isPrivileged;
	}
	public String getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(String rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	//toString() method to get proper println during testing
	@Override
	public String toString() {
		return "Buyer [buyerId=" + buyerId + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", password=" + password + ", isPrivileged=" + isPrivileged + ", rewardPoints=" + rewardPoints
				+ ", isActive=" + isActive + "]";
	}
	
	//If two objects are equal according to equals(...) then hashCode() returns same value
	@Override
	public int hashCode() {
		return Objects.hash(buyerId, email, isActive, isPrivileged, name, password, phoneNumber, rewardPoints);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Buyer other = (Buyer) obj;
		return Objects.equals(buyerId, other.buyerId) && Objects.equals(email, other.email)
				&& Objects.equals(isActive, other.isActive) && Objects.equals(isPrivileged, other.isPrivileged)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(phoneNumber, other.phoneNumber) && Objects.equals(rewardPoints, other.rewardPoints);
	}
	
	

}
