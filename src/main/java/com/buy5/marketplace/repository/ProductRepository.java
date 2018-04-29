package com.buy5.marketplace.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.buy5.marketplace.exceptions.InvalidOperationException;
import com.buy5.marketplace.model.Product;

@Repository
public class ProductRepository {
	
	List<Product> elements = new ArrayList<Product>();
	
	// GET: /products
	public List<Product> findAll() {
		return elements;
	}
	
	// GET: /product/byId/{id}
	public Optional<Product> findById(int pid) {
		return elements.stream()
					   .filter(p -> p.getId() == pid)
					   .findFirst();
	}
	
	// GET: /product/byName/{name}
	public Optional<Product> findByName(String prodName) {
		return elements.stream()
					   .filter(p -> p.getName().equalsIgnoreCase(prodName))
					   .findFirst();
	}
	
	// POST
	public void create(Product prod) {
		elements.add(prod);
	}

	// PUT
	public boolean update(Product prod) {
		Optional<Product> existingProd = findById(prod.getId());
		existingProd.ifPresent(p -> updateDetails(p , prod));
		return existingProd.isPresent();
	}
	
	public synchronized boolean adjustInvetory(Integer prodId, int adjustmentQuantity) {
		Optional<Product> existingProd = findById(prodId);
		if (existingProd.isPresent()) {
			Product prod = existingProd.get();
			final int newInventory = prod.getInventory() + adjustmentQuantity;
			
			// Don't let inventory fall below zero
			if (newInventory < 0)
				return false;
			else 
				prod.setInventory(newInventory);
		}
		
		return false;
	}
			
	private synchronized void updateDetails(Product origProd, Product prod) {
		origProd.setName(prod.getName());
		origProd.setDescription(prod.getDescription());
		origProd.setInventory(prod.getInventory());
		origProd.setPrice(prod.getPrice());
		origProd.setCategoryId(prod.getCategoryId());
	}
}