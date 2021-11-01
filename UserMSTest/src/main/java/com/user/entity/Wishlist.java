package com.user.entity;

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
 
}

