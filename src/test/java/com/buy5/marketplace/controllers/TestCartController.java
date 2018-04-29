package com.buy5.marketplace.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.buy5.marketplace.model.Order;

public class TestCartController {

	private static final RestTemplate restTmpltCart = new RestTemplate();
	private static final String BASE_URL = "http://localhost:8080";
	
	private static final String CART_ORDER_PUT_URI 		= "/order/{userName}/{productName}/{orderQuantity}";
	private static final String CART_ORDER_GET_URI 		= "/order/{orderId}";
	private static final String CART_ORDERS_GET_URI 	= "/orders";
		
	/**
	 * Test PUT : /order/{userName}/{productName}/{orderQuantity}
	 */
	@Test
	@org.springframework.core.annotation.Order(value=1)
	public void testFulfilOrderSuccess() {
		String uname = "lchawathe";
		String prodName;
		int quantity = 0;
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		{
			prodName = "iPhoneX";
			quantity = 5;
			Order order = new Order(uname, prodName, quantity);
							
			params.put("userName", uname);
			params.put("productName", prodName);
			params.put("orderQuantity", quantity);
		
			restTmpltCart.put(BASE_URL + CART_ORDER_PUT_URI, order, params);
		}
		
		{
			prodName = "galaxyNote9";
			quantity = 3;
			Order order = new Order(uname, prodName, quantity);
							
			params.put("userName", uname);
			params.put("productName", prodName);
			params.put("orderQuantity", quantity);
		
			restTmpltCart.put(BASE_URL + CART_ORDER_PUT_URI, order, params);
		}
	}
	
	@Test
	@org.springframework.core.annotation.Order(value=2)
	public void testFulfilOrderFailure() {
		String uname = "larry";
		String prodName;
		int quantity = 0;
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		{
			prodName = "SurfacePro";
			quantity = 15;
			Order order = new Order(uname, prodName, quantity);
							
			params.put("userName", uname);
			params.put("productName", prodName);
			params.put("orderQuantity", quantity);
		
			restTmpltCart.put(BASE_URL + CART_ORDER_PUT_URI, order, params);
		}
		
		{
			prodName = "e-myth";
			quantity = 5;
			Order order = new Order(uname, prodName, quantity);
							
			params.put("userName", uname);
			params.put("productName", prodName);
			params.put("orderQuantity", quantity);
		
			restTmpltCart.put(BASE_URL + CART_ORDER_PUT_URI, order, params);
		}
	}
		
	@Test
	@org.springframework.core.annotation.Order(value=2)
	public void testGetOrder()
	{
		final String GET_ORDER_URL = (BASE_URL + CART_ORDER_GET_URI);
		
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", 1);
		
		// Call 1 to get entire Order object
		Order order1 = restTmpltCart.getForObject(GET_ORDER_URL, Order.class, params);
		assertNotNull(order1);
		assertEquals("Order#1 created?", Integer.valueOf(1), order1.getId());
		
		// Call 2 to get only HTTPS status
		ResponseEntity<Order> response = restTmpltCart.getForEntity(GET_ORDER_URL, Order.class, params);
		HttpStatus httpStatus = response.getStatusCode();
		System.err.println("Response from "+ GET_ORDER_URL + " Status: "+ httpStatus);
		int statusCode = Integer.parseInt(httpStatus.toString());
		assertTrue("HTTP OK?", (statusCode >= 200 && statusCode <= 299));
	}
	
	@Test
	@org.springframework.core.annotation.Order(value=3)
	public void testGetAllOrders()
	{
		final String GET_ORDERS_URL = (BASE_URL + CART_ORDERS_GET_URI);
		
		// IMPORTANT: Custom types
		ParameterizedTypeReference<List<Order>> listOfStrings = new ParameterizedTypeReference<List<Order>>() {};
		ResponseEntity<List<Order>> response = restTmpltCart.exchange(GET_ORDERS_URL, HttpMethod.GET, null, listOfStrings);
		List<Order> existingOrders = response.getBody();
		
		assertTrue("Order created?", !existingOrders.isEmpty());
		existingOrders.stream().forEach(System.err::println);
	}

}
