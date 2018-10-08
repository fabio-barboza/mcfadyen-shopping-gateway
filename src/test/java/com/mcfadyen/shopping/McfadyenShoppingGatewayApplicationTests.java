package com.mcfadyen.shopping;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.mcfadyen.shopping.model.CommerceItem;
import com.mcfadyen.shopping.model.Product;
import com.mcfadyen.shopping.model.ShoppingCart;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class McfadyenShoppingGatewayApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void integrationTestListAllProducts() {

		ResponseEntity<List<Product>> responseEntity = restTemplate.exchange("/products", HttpMethod.GET,
				HttpEntity.EMPTY, new ParameterizedTypeReference<List<Product>>() {
				});
		List<Product> products = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(products.size(), 3);
	}

	@Test
	public void integrationTestGetShoppingCart() {

		ResponseEntity<ShoppingCart> responseEntity = restTemplate.getForEntity("/shoppingcart", ShoppingCart.class);
		ShoppingCart shoppingCart = responseEntity.getBody();

		assertEquals(shoppingCart.getItems().size(), 0);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void integrationTestAddItemShoppingCart() {

		ResponseEntity<List<Product>> responseEntityProducts = restTemplate.exchange("/products", HttpMethod.GET,
				HttpEntity.EMPTY, new ParameterizedTypeReference<List<Product>>() {
				});

		List<Product> products = responseEntityProducts.getBody();

		CommerceItem item = new CommerceItem();
		item.setProduct((Product) products.get(1));
		item.setQuantity(3);

		ResponseEntity<CommerceItem> responseEntity = restTemplate.postForEntity("/shoppingcart/items", item,
				CommerceItem.class);
		item = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(item.getAmmount().compareTo(new BigDecimal(30)), 0);
		assertEquals(item.getProduct().getName(), "Candy");
	}

}
