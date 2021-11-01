package com.product.service;

import java.util.List;

import com.product.dto.ProductDTO;

//Service interface which enables abstraction
public interface ProductService {
	
	public String addProduct(ProductDTO productDTO) throws Exception;
	
	public String deleteProduct(String id) throws Exception;
	
	public ProductDTO getProductByName(String name) throws Exception;
	
	public List<ProductDTO> getProductByCategory(String category) throws Exception;
	
	public ProductDTO getProductById(String id) throws Exception;

	public Boolean updateStockOfProdDelete(String prodId, Integer quantity) throws Exception;
	
	public Boolean updateStockOfProdAdd(String prodId, Integer quantity) throws Exception;
	
	public List<ProductDTO> viewAllProducts() throws Exception;

}
