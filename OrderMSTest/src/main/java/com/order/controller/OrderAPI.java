package com.order.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.dto.CartDTO;
import com.order.dto.OrderDTO;
import com.order.dto.PlacedOrderDTO;
import com.order.dto.ProductDTO;
import com.order.service.OrderService;

//Indicates that the application uses REST mappings
@RestController
public class OrderAPI {
	
	@Autowired
	private OrderService orderService;
	
	//URI to access other microservices
	@Value("${user.uri}")
	String userUri;
	
	@Value("${product.uri}")
	String productUri;
	
	//Add to cart method
	@PostMapping(value = "/addToCart/{buyerId}/{prodId}/{quantity}")
	public ResponseEntity<String> addProductToCart(@PathVariable String buyerId, @PathVariable String prodId, @PathVariable String quantity)
	{
		try {
			//As the cart table is in userdb, we call user microservice and then add to cart
			String message=new RestTemplate().postForObject(userUri+"/buyer/addToCart/"+buyerId+"/"+prodId+"/"+quantity, null, String.class);
			return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			if(e.getMessage().equals("404 null"))
			{
				return new ResponseEntity<String>("No products with productId", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<String>("Error", HttpStatus.UNAUTHORIZED);
		}
	}
	
	//Remove item from cart method
	@DeleteMapping(value = "/removeItemFromCart/{buyerId}/{prodId}")
	public ResponseEntity<String> removeItemFromCart(@PathVariable String buyerId, @PathVariable String prodId)
	{
		try {
			new RestTemplate().delete(userUri+"/buyer/removeItemFromCart/"+buyerId+"/"+prodId);
			return new ResponseEntity<>("Successfully deleted", HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			if(e.getMessage().equals("404 null"))
			{
				return new ResponseEntity<String>("No products with productId", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<String>("Error", HttpStatus.UNAUTHORIZED);
		}
	}

	//Get all orders method
	@GetMapping(value = "/viewOrders")
	public ResponseEntity<List<OrderDTO>> viewAllOrders()
	{
		try {
			List<OrderDTO> ordersList=orderService.viewAllOrders();
			return new ResponseEntity<>(ordersList, HttpStatus.OK);
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
	//Get orders by buyer id method
	@GetMapping(value = "/viewOrders/{buyerId}")
	public ResponseEntity<List<OrderDTO>> viewOrdersByBuyerId(@PathVariable String buyerId)
	{
		try {
			List<OrderDTO> ordersList=orderService.viewOrderByBuyerId(buyerId);
			return new ResponseEntity<>(ordersList, HttpStatus.OK);
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
	//Place order method
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/placeOrder/{buyerId}")
	public ResponseEntity<String> placeOrder(@PathVariable String buyerId, @RequestBody OrderDTO order)
	{
		try {
			ObjectMapper mapper=new ObjectMapper();
	
			//getting cart of the buyer
			List<CartDTO> cartList=mapper.convertValue(new RestTemplate().getForObject(userUri+"/buyer/getCart/"+buyerId, List.class), new TypeReference<List<CartDTO>>() {});
			
			//getting infomation about the products in buyer's cart
			List<ProductDTO> productList=new ArrayList<>();
			for(CartDTO cart:cartList)
			{
				ProductDTO productDto=new RestTemplate().getForObject(productUri+"/getById/"+cart.getProdId(), ProductDTO.class);
				productList.add(productDto);
						
			}
			PlacedOrderDTO orderPlaced=orderService.placeOrder(cartList, productList, order);
			for(CartDTO cartDto : cartList)
			{
				System.out.println(cartDto.getProdId()+" "+cartDto.getQuantity());
				new RestTemplate().put(productUri+"/updateStock/delete/"+cartDto.getProdId()+"/"+cartDto.getQuantity(), orderPlaced);
//				Boolean b=new RestTemplate().put(productUri+"/updateStock/delete/"+cartDto.getProdId()+"/"+cartDto.getQuantity(), Boolean.class);
//				new RestTemplate().postForObject(userUri+"/buyer/removeItemFromCart/"+buyerId+"/"+cartDto.getProdId(), null, String.class);
				new RestTemplate().delete(userUri+"/buyer/removeItemFromCart/"+buyerId+"/"+cartDto.getProdId());
			}
//			new RestTemplate().getForObject(userUri+"/updateRewardPoints/add/"+buyerId+"/"+orderPlaced.getRewardPoints(), String.class);
			new RestTemplate().put(userUri+"/updateRewardPoints/add/"+buyerId+"/"+orderPlaced.getRewardPoints(), orderPlaced);
			return new ResponseEntity<String>(orderPlaced.getOrderId(),HttpStatus.ACCEPTED);
		}
		catch (Exception e) {
			e.printStackTrace();
			String message="There was some error";
			if(e.getMessage().equals("404 null"))
			{
				message="Error while placing order";
			}
			return new ResponseEntity<String>(message,HttpStatus.NOT_FOUND);
		}
	}
}
