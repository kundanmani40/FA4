package com.order.service;

import java.util.List;

import com.order.dto.CartDTO;
import com.order.dto.OrderDTO;
import com.order.dto.PlacedOrderDTO;
import com.order.dto.ProductDTO;

//Service interface which enables abstraction
public interface OrderService {

	public List<OrderDTO> viewAllOrders() throws Exception;
	
	public List<OrderDTO> viewOrderByBuyerId(String buyerId) throws Exception;
	
	public PlacedOrderDTO placeOrder(List<CartDTO> cartList, List<ProductDTO> productList, OrderDTO order) throws Exception;
	
}
