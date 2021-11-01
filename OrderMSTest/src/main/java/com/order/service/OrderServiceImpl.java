package com.order.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.dto.CartDTO;
import com.order.dto.OrderDTO;
import com.order.dto.PlacedOrderDTO;
import com.order.dto.ProductDTO;
import com.order.entity.Order;
import com.order.entity.ProductsOrdered;
import com.order.repository.OrderRepository;
import com.order.repository.ProductsOrderedRepository;
import com.order.utility.MyPrimaryKey;

//Implementation of OrderService interface
@Service(value = "orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	private static int o;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductsOrderedRepository productsOrderedRepository;
	
	static {
		o=100;
	}
	
	//View all orders service
	@Override
	public List<OrderDTO> viewAllOrders() throws Exception {
		// TODO Auto-generated method stub
		Iterable<Order> allOrders=orderRepository.findAll();
		List<OrderDTO> ordersList=new ArrayList<>();
		for (Order order : allOrders) {
			OrderDTO orderDto=new OrderDTO();
			orderDto.setOrderId(order.getOrderId());
			orderDto.setBuyerId(order.getBuyerId());
			orderDto.setAmount(order.getAmount());
			orderDto.setDate(order.getDate());
			orderDto.setAddress(order.getAddress());
			orderDto.setStatus(order.getStatus());
			ordersList.add(orderDto);
		}
		if(ordersList.isEmpty())
		{
			throw new Exception("No Orders at all");
		}
		return ordersList;
	}

	//View order by buyer id service
	@Override
	public List<OrderDTO> viewOrderByBuyerId(String buyerId) throws Exception {
		// TODO Auto-generated method stub
		List<Order> allOrders=orderRepository.findByBuyerId(buyerId);
		if(allOrders.isEmpty())
		{
			throw new Exception("No order for given buyer id");
		}
		List<OrderDTO> ordersList=new ArrayList<>();
		for (Order order : allOrders) {
			OrderDTO orderDto=new OrderDTO();
			orderDto.setOrderId(order.getOrderId());
			orderDto.setBuyerId(order.getBuyerId());
			orderDto.setAmount(order.getAmount());
			orderDto.setDate(order.getDate());
			orderDto.setAddress(order.getAddress());
			orderDto.setStatus(order.getStatus());
			ordersList.add(orderDto);
		}
		return ordersList;
	}

	//Place order service
	@Override
	public PlacedOrderDTO placeOrder(List<CartDTO> cartList, List<ProductDTO> productList, OrderDTO orderDto) throws Exception {
		Order order=new Order();
		String id="O"+o++;
		order.setOrderId(id);
		order.setBuyerId(cartList.get(0).getBuyerId());
		order.setAmount(10f);
		order.setDate(LocalDate.now());
		order.setAddress(orderDto.getAddress());
		order.setStatus("PLACED");
		
		List<ProductsOrdered> productsOrderedList=new ArrayList<>();
		for(int i=0;i<cartList.size();i++)
		{
			order.setAmount(order.getAmount()+(cartList.get(i).getQuantity()*productList.get(i).getPrice()));
			ProductsOrdered productsOrdered=new ProductsOrdered();
			productsOrdered.setMyPrimaryKey(new MyPrimaryKey(cartList.get(i).getBuyerId(), productList.get(i).getProdId()));
			productsOrdered.setSellerId(productList.get(i).getSellerId());
			productsOrdered.setQuantity(cartList.get(i).getQuantity());
			productsOrderedList.add(productsOrdered);
		}
		productsOrderedRepository.saveAll(productsOrderedList);
		orderRepository.save(order);
		PlacedOrderDTO placedOrder=new PlacedOrderDTO();
		placedOrder.setBuyerId(order.getBuyerId());
		placedOrder.setOrderId(order.getOrderId());
		Integer points=(int) (order.getAmount()/100);
		placedOrder.setRewardPoints(points);
		return placedOrder;
		
	}

}