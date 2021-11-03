package com.oms.order.service;

import java.util.List;

import com.oms.order.dto.CartDTO;
import com.oms.order.dto.OrderDTO;
import com.oms.order.dto.PlacedOrderDTO;
import com.oms.order.dto.ProductDTO;
import com.oms.order.exception.OrderMsException;

public interface OrderService {
	
	public List<OrderDTO> viewAllOrders() throws Exception;
	
	public List<OrderDTO> viewOrderByBuyerId(String buyerId) throws Exception;
	
	public PlacedOrderDTO placeOrder(String buyerMode, List<CartDTO> cartList, List<ProductDTO> productList, OrderDTO order) throws Exception;


}
