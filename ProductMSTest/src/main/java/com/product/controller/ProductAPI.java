package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.product.dto.ProductDTO;
import com.product.service.ProductService;

//Indicates that the application uses REST methods
@RestController
@CrossOrigin
public class ProductAPI {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private Environment environment;
	
	//Getting all the products
	@GetMapping(value = "/viewAllProducts")
	public ResponseEntity<List<ProductDTO>> viewAllProducts()
	{
		try {
			List<ProductDTO> list = productService.viewAllProducts();
			return new ResponseEntity<>(list,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,environment.getProperty(e.getMessage()),e);
		}
	}
	
	//Getting product by product name
	@GetMapping(value ="/getByName/{name}")
	public ResponseEntity<ProductDTO> getByProductName(@PathVariable String name)
	{
		try {
			ProductDTO productDTO = productService.getProductByName(name);
			return new ResponseEntity<>(productDTO,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,environment.getProperty(e.getMessage()), e);
		}
	}
	
	//Getting product by product id
	@GetMapping(value = "/getById/{prodId}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable String prodId)
	{
		try {
			ProductDTO productDto=productService.getProductById(prodId);
			return new ResponseEntity<>(productDto, HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()));
		}
	}
	
	//Getting product by product category
	@GetMapping(value = "/getByCategory/{name}")
	public ResponseEntity<List<ProductDTO>> getByProductCategory(@PathVariable String name)
	{
		try {
			List<ProductDTO> productDTO = productService.getProductByCategory(name);
			return new ResponseEntity<List<ProductDTO>>(productDTO,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,environment.getProperty(e.getMessage()), e);
		}
	}
	
    //Adding product 
	@PostMapping(value = "/addProduct")
	public ResponseEntity<String> addProduct(@RequestBody ProductDTO prod)
	{	
		try {
			String msg = productService.addProduct(prod);
			return new ResponseEntity<>("The New Product Added With Product Id "+msg,HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(environment.getProperty(e.getMessage()),HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	//Deleting product based on product id
	@DeleteMapping(value = "/deleteProduct/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable String id){
		
		try {
			String msg = productService.deleteProduct(id);
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(environment.getProperty(e.getMessage()),HttpStatus.NOT_FOUND);
		}
	}
	
	//Updating Stock delete method
	@PutMapping(value = "/updateStock/delete/{prodId}/{quantity}")
	public ResponseEntity<Boolean> updateStockDelete(@PathVariable String prodId, @PathVariable Integer quantity)
	{		
		try {
			Boolean status = productService.updateStockOfProdDelete(prodId,quantity);
			return new ResponseEntity<>(status,HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,environment.getProperty(e.getMessage()), e);
		}		
	}
	
	//Updating Stock add method
	@PutMapping(value = "/updateStock/add/{prodId}/{quantity}")
	public ResponseEntity<Boolean> updateStockAdd(@PathVariable String prodId, @PathVariable Integer quantity)
	{		
		try {
			Boolean status = productService.updateStockOfProdAdd(prodId,quantity);
			return new ResponseEntity<>(status,HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,environment.getProperty(e.getMessage()), e);
		}		
	}

}
