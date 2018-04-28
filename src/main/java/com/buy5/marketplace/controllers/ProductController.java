package com.buy5.marketplace.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.buy5.marketplace.exceptions.NotFoundException;
import com.buy5.marketplace.model.Product;
import com.buy5.marketplace.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

@RestController
public class ProductController extends Buy5Controller {
	@Autowired
	private ProductRepository prodRepo;
	
	@GetMapping(path="/products"
			   ,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getAllProducts() {
		return this.prodRepo.findAll();
	}
	
	/**
	 * @see Buy5Controller#handleIllegalArgumentException(NotFoundException, javax.servlet.http.HttpServletResponse)
	 */
	@GetMapping(path="/product/id/{id}"
			   ,produces=MediaType.APPLICATION_JSON_VALUE)
	public Product getProductById(@PathVariable(name="id") Integer pid)
					throws NotFoundException
	{
		Optional<Product> optionalRes = this.prodRepo.findById(pid);
		 if (optionalRes.isPresent())
			 return optionalRes.get();
		 else
			 throw new NotFoundException(Product.class, "Product by Id "+pid+" not found."); 
	}
	
	/**
	 * @see Buy5Controller#handleIllegalArgumentException(NotFoundException, javax.servlet.http.HttpServletResponse)
	 */
	@GetMapping(path="/product/name/{name}"
			   ,produces=MediaType.APPLICATION_JSON_VALUE)
	public Product getProductById(@PathVariable(name="name") String prodName)
						throws NotFoundException
	{
		Optional<Product> optionalRes = this.prodRepo.findByName(prodName);
		 if (optionalRes.isPresent())
			 return optionalRes.get();
		 else
			 throw new NotFoundException(Product.class, "Product by Name "+prodName+" not found."); 
	}
	
	@PostMapping(path="/product"
			    ,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Product createProduct(@RequestBody Product p) {
		throw new RuntimeException("Implement POST");
	}
	
	@PutMapping(path="/product"
				,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Product updateProduct(@RequestBody Product p) {
		throw new RuntimeException("Implement PUT");
	}
	
	private boolean createOrUpdateProduct(Product p) {
		try {
			getProductById(p.getId());
			this.prodRepo.update(p);
			return false;
		} catch (NotFoundException prodNotFoundEx) {
			this.prodRepo.create(p);
			return true;
		}
	}
}