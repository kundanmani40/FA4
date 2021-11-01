package com.order.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.order.entity.Order;

//Repository interface for 'OrderRepository' which extends from CRUD Repository
public interface OrderRepository extends CrudRepository<Order, String> {

	public List<Order> findByBuyerId(String buyerId);
	
}
