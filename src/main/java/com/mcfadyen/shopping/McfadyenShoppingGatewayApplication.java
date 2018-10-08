package com.mcfadyen.shopping;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mcfadyen.shopping.model.Product;
import com.mcfadyen.shopping.repository.ProductRepository;

@SpringBootApplication
public class McfadyenShoppingGatewayApplication implements CommandLineRunner{
	
	@Autowired
	private ProductRepository repository;	
	
	@Autowired
	private EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(McfadyenShoppingGatewayApplication.class, args);
	}
	
	/**
	 * Method executed everytime that the applications run at first time.
	 */
	@Override
	@Transactional
	public void run(String... args) throws Exception {

		Product p1 = new Product("Candy", "https://io.convertiez.com.br/m/drogal/shop/products/images/5730013/medium/candy-crush-fini-90g_21552.jpg", new BigDecimal(10));
		Product p2 = new Product("Chocolate", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/Chocolate.jpg/260px-Chocolate.jpg", new BigDecimal(30));		
		Product p3 = new Product("Cake", "https://www.bbcgoodfood.com/sites/default/files/styles/recipe/public/recipe/recipe-image/2018/02/easter-nest-cake.jpg", new BigDecimal(50));				

		repository.saveAll(Arrays.asList(p1, p2, p3));
		
		em.createNativeQuery("UPDATE product SET id = 'da0ecdfa-4176-4660-bfd5-431e10628560' WHERE name = 'Cake'").executeUpdate();
		em.createNativeQuery("UPDATE product SET id = '3e88fd98-833e-42b2-9ecb-e3ecfff3970c' WHERE name = 'Candy'").executeUpdate();
		em.createNativeQuery("UPDATE product SET id = '1e7295f6-0c0b-4bb1-811e-0508328ff3af' WHERE name = 'Chocolate'").executeUpdate();
		
		em.flush();
	}		
}
