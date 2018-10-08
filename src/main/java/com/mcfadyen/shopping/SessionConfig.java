package com.mcfadyen.shopping;

import java.util.LinkedHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import com.mcfadyen.shopping.model.ShoppingCart;

@Configuration
public class SessionConfig {

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public LinkedHashMap<String, Object> userSession() {
		LinkedHashMap<String, Object> session = new LinkedHashMap<String, Object>();
		session.put("shoppingCart", new ShoppingCart());
		return session;
	}

}