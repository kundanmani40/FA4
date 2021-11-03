package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.user.dto.BuyerDTO;
import com.user.dto.CartDTO;
import com.user.dto.ProductDTO;
import com.user.dto.SellerDTO;
import com.user.service.UserService;

//Indicates that the microservices uses REST mappings
@RestController
public class UserAPI {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Environment environment;
	
	
	private KafkaTemplate<String, String> kafkaTemplate;

	private static final String TOPIC = "kafka-example";

			
	@GetMapping("/publish/{message}") public String post(@PathVariable("message")
			  final String message) {
			  
			  kafkaTemplate.send(TOPIC,message);
			  
			  System.out.println("published");
			  
			  return "Published successfully"; }

	
	//URI to use other microservices
	@Value("${product.uri}")
	String prodUri;
	
	@PostMapping(value = "/buyer/register")
	public ResponseEntity<String> registerBuyer(@RequestBody BuyerDTO buyerDto){
		
		try {
		String s ="Buyer registered successfully, buyer Id : " + userService.buyerRegistration(buyerDto);
		return new ResponseEntity<>(s,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,environment.getProperty(e.getMessage()),e);
		}
	}
	
	@PostMapping(value = "/seller/register")
	public ResponseEntity<String> registerSeller(@RequestBody SellerDTO sellerDto){
		
		try {
		String s ="Seller registered successfully, seller Id : "+ userService.sellerRegistration(sellerDto);
		return new ResponseEntity<>(s,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,environment.getProperty(e.getMessage()),e);
		}

	}
	
	@PostMapping(value = "/buyer/login/{email}/{password}")
	public ResponseEntity<String> loginBuyer(@PathVariable String email, @PathVariable String password)
	{
		try {
			String msg = userService.buyerLogin(email, password);
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,environment.getProperty(e.getMessage()),e);
		}
	}
	
	@PostMapping(value = "/seller/login/{email}/{password}")
	public ResponseEntity<String> loginSeller(@PathVariable String email, @PathVariable String password)
	{
		try {
			String msg = userService.sellerLogin(email, password);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,environment.getProperty(e.getMessage()),e);
		}
	}
	
	@DeleteMapping(value = "/buyer/deactivate/{id}")
	public ResponseEntity<String> deleteBuyerAccount(@PathVariable String id) throws Exception{
		
		String msg = userService.deleteBuyer(id);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/seller/deactivate/{id}")
	public ResponseEntity<String> deleteSellerAccount(@PathVariable String id) throws Exception{
		String msg = userService.deleteSeller(id);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}

	@PostMapping(value = "/buyer/addToWishlist/{buyerId}/{prodId}")
	public ResponseEntity<String> addProductToWishlist(@PathVariable String buyerId, @PathVariable String prodId) throws Exception
	{
		try {	
			ProductDTO product = new RestTemplate().getForObject(prodUri+"/getById/"+prodId, ProductDTO.class);
			String msg = userService.AddToWishlist(product.getProdId(), buyerId);
			
			//for testing
			//String msg = userService.AddToWishlist(prodId, buyerId);
			
			return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			String newMsg = "There was some error";
			if(e.getMessage().equals("404 null"))
			{
				newMsg = "Product or Buyer ID doesn't exists";
			}
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,newMsg,e);
		}
	}
	
	@DeleteMapping(value = "/buyer/removeFromWishlist/{buyerId}/{prodId}")
	public ResponseEntity<String> removeProductFromWishlist(@PathVariable String buyerId, @PathVariable String prodId) throws Exception
	{
		try
		{
			String messgae=userService.removeFromWishlist(buyerId, prodId);
			return new ResponseEntity<>(messgae, HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
	@GetMapping(value = "/seller")
	public ResponseEntity<List<SellerDTO>> getAllSeller() throws Exception{
		try
		{
			List<SellerDTO> sellerList=userService.findAllSeller();
			return new ResponseEntity<>(sellerList,HttpStatus.OK);
		}
		catch(Exception e)
		{
			String newMsg = "There was some error";
			if(e.getMessage().equals("404 null"))
			{
			  newMsg = "There are no Seller Present Here";
			}
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,newMsg,e);
		}
	}
	
	@PutMapping(value = "/buyer/mode/{buyerId}")
	public ResponseEntity<String> setUserMode(@PathVariable String buyerId) throws Exception{
		
		String msg = userService.userMode(buyerId);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	//Method to get cart
	@GetMapping(value = "/buyer/getCart/{buyerId}")
	public ResponseEntity<List<CartDTO>> getProductListFromCart(@PathVariable String buyerId) throws Exception{
		try
		{
			List<CartDTO> cartList=userService.getCart(buyerId);
			return new ResponseEntity<>(cartList,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

	//Method to add product to cart
	@PostMapping(value = "/buyer/addToCart/{buyerId}/{prodId}/{quantity}")
	public ResponseEntity<String> addProductToCart(@PathVariable String buyerId, @PathVariable String prodId, @PathVariable Integer quantity) throws Exception
	{
		try {
			ProductDTO productDto=new RestTemplate().getForObject(prodUri+"/getById/"+prodId, ProductDTO.class);
			System.out.println(productDto);
			String message=userService.addToCart(buyerId, prodId, quantity);
			return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			String newMessage="Error";
			if(e.getMessage().equals("404 null"))
			{
				newMessage="No producst with productId";
			}
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, newMessage, e);
		}
	}
	
	//Method to empty a cart for a particular buyer
	@DeleteMapping(value = "/buyer/emptyMyCart/{buyerId}")
	public ResponseEntity<String> emptyMyCart(@PathVariable String buyerId) throws Exception
	{
		try {
			String message=userService.emptyMyCart(buyerId);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
	//Method to remove a item from cart
	@DeleteMapping(value = "/buyer/removeItemFromCart/{buyerId}/{prodId}")
	public ResponseEntity<String> removeProductFromCart(@PathVariable String buyerId, @PathVariable String prodId) throws Exception
	{
		try
		{
			String messgae=userService.removeItemFromCart(buyerId, prodId);
			return new ResponseEntity<>(messgae, HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
	//Method to delete reward points
	@PutMapping(value = "/updateRewardPoints/delete/{buyerId}/{points}")
	public ResponseEntity<String> updateRewardPointsDelete(@PathVariable String buyerId, @PathVariable Integer points) throws Exception
	{
		try {
			String message=userService.updateRewardPointsDelete(buyerId, points);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
	//Method to add reward points
	@PutMapping(value = "/updateRewardPoints/add/{buyerId}/{points}")
	public ResponseEntity<String> updateRewardPointsAdd(@PathVariable String buyerId, @PathVariable Integer points) throws Exception
	{
		try {
			String message=userService.updateRewardPointsAdd(buyerId, points);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
}
