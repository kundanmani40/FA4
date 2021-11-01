package com.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.user.entity.Seller;

public interface SellerRepository extends CrudRepository<Seller, String> {
	
	public Seller findByPhoneNumber(String phoneNumber);
	
	public Seller findByEmail(String email);
	
	public Seller findBySellerId(String id);

}

