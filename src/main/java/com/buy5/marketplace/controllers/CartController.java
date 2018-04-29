package com.buy5.marketplace.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buy5.marketplace.exceptions.NotFoundException;
import com.buy5.marketplace.model.Order;
import com.buy5.marketplace.repository.CartRepository;

/**
 * Orchestration Service
 * 
 * @author LChawathe
 */
@RestController
public class CartController extends Buy5Controller {
	@Autowired
	private CartRepository cartRepo; 
	
	@PostMapping(path="/order/{userName}/{productName}/{orderQuantity}")
	public Order fulfilOrder(@PathVariable(name="userName") String userName,
							   @PathVariable(name="productName") String productName,
							   @PathVariable(name="orderQuantity") int quatity) {
		return cartRepo.updateOrder(userName, productName, quatity);
	}
	
	@GetMapping(path="/orders")
	public List<Order> getAllOrders() {
		return cartRepo.findAllOrders();
	}
	
	@GetMapping(path="/order/{orderId}")
	public Order getOrder( @PathVariable(name="orderId") Integer orderId)
					throws NotFoundException
	{
		System.err.println("OrderId=="+ orderId);
		Optional<Order> optOrder = cartRepo.findOrder(orderId);
		if (optOrder.isPresent()) {
			return optOrder.get();
		} else {
			throw new NotFoundException(Order.class, "Order : "+ orderId + " not available.");
		}
	}
}
