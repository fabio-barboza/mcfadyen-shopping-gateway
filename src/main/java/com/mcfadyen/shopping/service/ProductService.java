package com.mcfadyen.shopping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcfadyen.shopping.exception.ObjectNotFoundException;
import com.mcfadyen.shopping.model.Product;
import com.mcfadyen.shopping.repository.ProductRepository;

/**
 * The Product Service
 * 
 * @author fabio
 *
 */
@Service
public class ProductService {

	/**
	 * The entity manager
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * The repository injection
	 */
	@Autowired
	private ProductRepository repository;

	/**
	 * The Product List
	 * 
	 * @return A List with all Products in alphabetic order.
	 */
	public List<Product> listAll() {
		List<Product> result = new ArrayList<Product>();
		repository.findAll().forEach(result::add);
		return result.stream().sorted((u1, u2) -> u1.getName().compareTo(u2.getName())).collect(Collectors.toList());
	}
	
	/**
	 * Search by ID
	 * 
	 * @param id The product ID
	 * @return The Product entity
	 */
	public Product find(String id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Entity not found"));
	}
	
	/**
	 * Save the Product
	 * 
	 * @param product The Product to be persisted
	 * @return The persisted Product
	 */
	public Product save(Product product) {
		return repository.save(product);
	}
	
	/**
	 * remove a Product
	 * 
	 * @param product The Product to be removed.
	 */
	public void delete(Product product) {
		repository.delete(product);
	}

}
