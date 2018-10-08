package com.mcfadyen.shopping.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mcfadyen.shopping.model.Product;
import com.mcfadyen.shopping.service.ProductService;

import io.swagger.annotations.ApiOperation;

/**
 * The Class responsible to control the Products requests.
 * 
 * @author fabio
 *
 */
@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	/**
	 * The service Injection
	 */
	@Autowired
	private ProductService service;

	/**
	 * Find by ID
	 * 
	 * @param id The Product ID
	 * 
	 * @return The Product. @see Product.class
	 */
	@ApiOperation(value = "Return a Product.", response = Product.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable String id) {
		return ResponseEntity.ok().body(service.find(id));
	}

	/**
	 * List all Products in alphabetic order.
	 * 
	 * @return A Product list @see Product.class
	 */
	@ApiOperation(value = "Returns information about the *McFadyen* products.", response = Product.class)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> list() {
		return ResponseEntity.ok().body(service.listAll());
	}

	/**
	 * Insert a new Product
	 * 
	 * @param product The product to persist
	 * 
	 * @return The persisted Product.
	 */
	@ApiOperation(value = "Insert a new Product", response = Product.class)
	@PostMapping
	public ResponseEntity<?> insert(@Valid @RequestBody Product product) {
		return ResponseEntity.ok().body(service.save(product));
	}

	/**
	 * Update a Product
	 * 
	 * @param id The Product ID.
	 * 
	 * @param product The product to be edited.
	 * 
	 * @return The persisted Product.
	 */
	@ApiOperation(value = "Updates the Product information", response = Product.class)
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody Product product) {
		Product editedProduct = service.find(id);

		editedProduct.setName(product.getName());
		editedProduct.setImage(product.getImage());
		editedProduct.setPrice(product.getPrice());

		return ResponseEntity.ok().body(service.save(editedProduct));
	}

	/**
	 * Removes a Product
	 * 
	 * @param id The Product to be removed.
	 */
	@ApiOperation(value = "Removes a Product")
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable String id) {
		Product product = new Product();
		product.setId(id);
		service.delete(product);
	}

}
