package com.product.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.product.entity.Product;

//Repository interface for 'ProductRepository' which extends from CRUD Repository
public interface ProductRepository extends CrudRepository<Product, String> {

	public Product findByProdId(String prodId);
	
	public Product findByProductName(String name);
	
	public List<Product> findByCategory(String category);
	
	public List<Product> findAll();
	
}
