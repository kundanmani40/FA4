package com.oms.order.service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oms.order.dto.CartDTO;
import com.oms.order.dto.OrderDTO;
import com.oms.order.dto.PlacedOrderDTO;
import com.oms.order.dto.ProductDTO;
import com.oms.order.entity.Order;
import com.oms.order.entity.ProductsOrdered;
import com.oms.order.repository.OrderRepository;
import com.oms.order.repository.ProductsOrderedRepository;
import com.oms.order.utility.CustomPK;

@Service(value = "orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
	
//	@KafkaListener(topics = "Test", groupId = "group_id")
//    public void consume(String message) {
//        System.out.println("Consumed message: " + message);
//        String[] array=message.split(" ");
//        for(int i=0; i<array.length ;i++)
//        {
//        	System.out.println(array[i]);
//        }
//    }
	
	private int o;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductsOrderedRepository prodOrderedRepository;
	
//	static {
//		o=100;
//	}

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
	public PlacedOrderDTO placeOrder(String buyerMode, List<CartDTO> cartList, List<ProductDTO> productList, OrderDTO orderDto) throws Exception {
		Order order=new Order();
		
		//Method1
		o=100;
		while(true)
		{
			String id="O"+o;
			Order orderCheck=orderRepository.findByOrderId(id);
			if(orderCheck == null)
			{
				order.setOrderId(id);
				break;
			}
			o++;
		}
		
		//Method2
		/*while(true)
		{
			Random random=new Random();
			String id="O"+random.nextInt();
			Order orderCheck=orderRepository.findByOrderId(id);
			if(orderCheck == null)
			{
				order.setOrderId(id);
				break;
			}
		}*/
		
		order.setBuyerId(cartList.get(0).getBuyerId());
		order.setAmount(50f);
		if(buyerMode.equals("True"))
		{
			order.setAmount(0f);
		}
		order.setDate(LocalDate.now());
		order.setAddress(orderDto.getAddress());
		order.setStatus("PLACED");
		
		List<ProductsOrdered> productsOrderedList=new ArrayList<>();
		for(int i=0;i<cartList.size();i++)
		{
			order.setAmount(order.getAmount()+(cartList.get(i).getQuantity()*productList.get(i).getPrice()));
			ProductsOrdered productsOrdered=new ProductsOrdered();
			productsOrdered.setPrimaryKeys(new CustomPK(cartList.get(i).getBuyerId(), productList.get(i).getProdId()));;
			productsOrdered.setSellerId(productList.get(i).getSellerId());
			productsOrdered.setQuantity(cartList.get(i).getQuantity());
			productsOrderedList.add(productsOrdered);
		}
		prodOrderedRepository.saveAll(productsOrderedList);
		orderRepository.save(order);
		PlacedOrderDTO placedOrder=new PlacedOrderDTO();
		placedOrder.setBuyerId(order.getBuyerId());
		placedOrder.setOrderId(order.getOrderId());
		Integer points=(int) (order.getAmount()/100);
		placedOrder.setRewardPoints(points);
		return placedOrder;
			
	}
	
	@Override
	public String generateKafkaOrder(String buyerId, String buyerMode) throws Exception{
		Order order=new Order();
		
		//Method1
		o=100;
		while(true)
		{
			String id="O"+o;
			Order orderCheck=orderRepository.findByOrderId(id);
			if(orderCheck == null)
			{
				order.setOrderId(id);
				break;
			}
			o++;
		}
		
		//Method2
		/*while(true)
		{
			Random random=new Random();
			String id="O"+random.nextInt();
			Order orderCheck=orderRepository.findByOrderId(id);
			if(orderCheck == null)
			{
				order.setOrderId(id);
				break;
			}
		}*/
		
		order.setBuyerId(buyerId);
		order.setAmount(50f);
		if(buyerMode.equals("True"))
		{
			order.setAmount(0f);
		}
		order.setDate(LocalDate.now());
		order.setAddress("Un-defined");
		order.setStatus("In Order");
		
		orderRepository.save(order);
		return order.getOrderId();
	}

	@Override
	public PlacedOrderDTO placeKafkaOrder(String orderId, ProductDTO productDto, String buyerId, Integer quantity) throws Exception{
		Order order=orderRepository.findByOrderId(orderId);
		if(order == null)
		{
			throw new Exception("Order with id: "+orderId+" does not exists");
		}
		order.setAmount(order.getAmount()+(quantity*productDto.getPrice()));
		ProductsOrdered productsOrdered=new ProductsOrdered();
		productsOrdered.setPrimaryKeys(new CustomPK(buyerId, productDto.getProdId()));;
		productsOrdered.setSellerId(productDto.getSellerId());
		productsOrdered.setQuantity(quantity);
		
		prodOrderedRepository.save(productsOrdered);
		orderRepository.save(order);
		PlacedOrderDTO placedOrder=new PlacedOrderDTO();
		placedOrder.setBuyerId(order.getBuyerId());
		placedOrder.setOrderId(order.getOrderId());
		Integer points=(int) (order.getAmount()/100);
		placedOrder.setRewardPoints(points);
		return placedOrder;
	}
	
}
