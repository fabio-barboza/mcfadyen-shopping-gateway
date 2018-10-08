package com.mcfadyen.shopping.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcfadyen.shopping.model.CommerceItem;
import com.mcfadyen.shopping.service.ShoppingCartService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/shoppingcart")
public class ShoppingCartResource {

	/**
	 * The service Injection
	 */
	@Autowired
	private ShoppingCartService service;

	@ApiOperation(value = "Returns the current shopping cart for the session. (shoppingcartGet)")
	@GetMapping
	public ResponseEntity<?> getShoppingCart() {
		return ResponseEntity.ok().body(service.getShoppingCart());
	}

	@ApiOperation(value = "Adds an item to the shopping cart. (shoppingcartItemsPost)")
	@PostMapping("/items")
	public ResponseEntity<?> addItem(@Valid @RequestBody CommerceItem commerceItem) {
		return ResponseEntity.ok().body(service.addItem(commerceItem));
	}

	@ApiOperation(value = "Removes a commerce item from the shopping cart, by commerce item id.")
	@DeleteMapping("/items/{id}")
	public ResponseEntity<?> removeItem(@PathVariable String id) {
		service.deleteItem(id);
		return ResponseEntity.ok().body("");
	}

}
