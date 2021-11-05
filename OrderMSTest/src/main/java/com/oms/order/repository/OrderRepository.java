package com.oms.order.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.oms.order.entity.Order;

public interface OrderRepository extends CrudRepository<Order, String>{

	public List<Order> findByBuyerId(String buyerId);
	
	public Order findByOrderId(String orderId);

}
