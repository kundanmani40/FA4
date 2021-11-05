package com.user.entity;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.user.utility.MyPrimaryKey;

@Entity
@Table(name = "wishlist")
public class Wishlist {
	
	@EmbeddedId
	private MyPrimaryKey myPrimaryKey ;

	public MyPrimaryKey getMyPrimaryKey() {
		return myPrimaryKey;
	}

	public void setMyPrimaryKey(MyPrimaryKey myPrimaryKey) {
		this.myPrimaryKey = myPrimaryKey;
	}

	@Override
	public String toString() {
		return "Wishlist [myPrimaryKey=" + myPrimaryKey + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(myPrimaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wishlist other = (Wishlist) obj;
		return Objects.equals(myPrimaryKey, other.myPrimaryKey);
	}
 
	
}

