package com.product.repository;

import org.springframework.data.repository.CrudRepository;

import com.product.entity.SubscribedProduct;
import com.product.utility.MyPrimaryKey;

//Repository interface for 'SubscribedProductRepository' which extends from CRUD Repository
public interface SubscribedProductRepository extends CrudRepository<SubscribedProduct, MyPrimaryKey> {
	
	

}
