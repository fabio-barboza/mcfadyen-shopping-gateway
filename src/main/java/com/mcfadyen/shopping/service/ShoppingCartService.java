package com.mcfadyen.shopping.service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcfadyen.shopping.model.CommerceItem;
import com.mcfadyen.shopping.model.ShoppingCart;

@Service
public class ShoppingCartService {

	@Autowired
	private LinkedHashMap<String, Object> userSession;

	/**
	 * Recalculate the Shopping Cart
	 */
	private void recalculateShoppingCart() {
		ShoppingCart shoppingCart = (ShoppingCart) userSession.get("shoppingCart");			
		
		BigDecimal total = shoppingCart.getItems().stream().map(CommerceItem::getAmmount).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		
		shoppingCart.setAmmount(total);
	}

	/**
	 * Return the current user Shopping Cart
	 * 
	 * @return The Shopping Cart
	 */
	public ShoppingCart getShoppingCart() {
		return (ShoppingCart) userSession.get("shoppingCart");
	}

	/**
	 * Add a CommerceItem to the user Shopping Cart
	 * 
	 * @param commerceItem The CommerceITem
	 * @return The added CommerceItem
	 */
	public CommerceItem addItem(CommerceItem commerceItem) {
		ShoppingCart shoppingCart = (ShoppingCart) userSession.get("shoppingCart");	
		
		CommerceItem searchItem = shoppingCart.getItems().stream()
				.filter((item) -> item.getId().equals(commerceItem.getId())).findFirst()
				.orElse(null);

		if (searchItem == null) {
			searchItem = new CommerceItem();
			searchItem.setId(UUID.randomUUID().toString());
			searchItem.setProduct(commerceItem.getProduct());
			searchItem.setQuantity(commerceItem.getQuantity());
			searchItem.setAmmount(
					commerceItem.getProduct().getPrice().multiply(new BigDecimal(commerceItem.getQuantity())));
			shoppingCart.getItems().add(searchItem);
		} else {
			searchItem.setQuantity(searchItem.getQuantity() + commerceItem.getQuantity());
			searchItem
					.setAmmount(searchItem.getProduct().getPrice().multiply(new BigDecimal(searchItem.getQuantity())));
		}
		
		recalculateShoppingCart();

		return searchItem;
	}

	/**
	 * Remove a CommerceItem from the user Shopping Cart
	 * 
	 * @param id The CommerceItem ID
	 */
	public void deleteItem(String id) {
		ShoppingCart shoppingCart = (ShoppingCart) userSession.get("shoppingCart");		
		
		shoppingCart.setItems(shoppingCart.getItems().stream().filter((item) -> !item.getId().equals(id))
				.collect(Collectors.toList()));
		
		recalculateShoppingCart();	
	}

}
