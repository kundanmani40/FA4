package com.user.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.user.entity.Cart;
import com.user.utility.MyPrimaryKey;

//Repository interface for 'CartRepository' which extends from CRUD Repository
public interface CartRepository extends CrudRepository<Cart, MyPrimaryKey> {

	public List<Cart> findByMyPrimaryKeyBuyerId(String buyerId);
	
	public void deleteByMyPrimaryKeyBuyerIdAndMyPrimaryKeyProdId(String buyerId, String prodId);
	
	public Cart findByMyPrimaryKeyBuyerIdAndMyPrimaryKeyProdId(String buyerId, String prodId);
	
	public void deleteByMyPrimaryKeyBuyerId(String buyerId);
	
}
