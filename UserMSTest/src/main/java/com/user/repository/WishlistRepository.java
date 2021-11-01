package com.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.user.entity.Wishlist;
import com.user.utility.MyPrimaryKey;


public interface WishlistRepository extends CrudRepository<Wishlist, MyPrimaryKey> {

	public void deleteByMyPrimaryKeyBuyerIdAndMyPrimaryKeyProdId(String buyerId, String prodId);
	
	public Wishlist findByMyPrimaryKeyBuyerIdAndMyPrimaryKeyProdId(String buyerId, String prodId);
}
