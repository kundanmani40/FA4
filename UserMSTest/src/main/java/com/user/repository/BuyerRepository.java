package com.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.user.entity.Buyer;

//Repository interface for 'BuyerRepository' which extends from CRUD Repository
public interface BuyerRepository extends  CrudRepository<Buyer, String>{
	
	public Buyer findByBuyerId(String buyerId) throws Exception;
	
	public Buyer findByPhoneNumber(String phoneNumber);
	
	public Buyer findByEmail(String email);

}
