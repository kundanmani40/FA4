package com.oms.order.service;

import java.util.List;

import com.oms.order.dto.CartDTO;
import com.oms.order.dto.OrderDTO;
import com.oms.order.dto.PlacedOrderDTO;
import com.oms.order.dto.ProductDTO;

public interface OrderService {
	
	public List<OrderDTO> viewAllOrders() throws Exception;
	
	public List<OrderDTO> viewOrderByBuyerId(String buyerId) throws Exception;
	
	public PlacedOrderDTO placeOrder(String buyerMode, List<CartDTO> cartList, List<ProductDTO> productList, OrderDTO order) throws Exception;

	public String generateKafkaOrder(String buyerId, String buyerMode) throws Exception;
	
	public PlacedOrderDTO placeKafkaOrder(String orderId, ProductDTO productDto, String buyerId, Integer quantity) throws Exception;
	
}
