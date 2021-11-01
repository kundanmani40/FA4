package com.order.repository;

import org.springframework.data.repository.CrudRepository;

import com.order.entity.ProductsOrdered;
import com.order.utility.MyPrimaryKey;

//Repository interface for 'ProductsOrderedRepository' which extends from CRUD Repository
public interface ProductsOrderedRepository extends CrudRepository<ProductsOrdered, MyPrimaryKey> {

}
