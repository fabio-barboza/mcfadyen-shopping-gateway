package com.mcfadyen.shopping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mcfadyen.shopping.model.Product;

/**
 * Product Repository
 * 
 * @author fabio
 *
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, String>  {

}