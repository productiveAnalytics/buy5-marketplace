package com.buy5.marketplace.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.buy5.marketplace.exceptions.ProductNotFoundException;
import com.buy5.marketplace.model.Product;
import com.buy5.marketplace.repository.ProductRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@RestController
public class ProductController {
	@Autowired
	private ProductRepository prodRepo;
	
	@GetMapping(path="/products"
			   ,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getAllProducts() {
		return this.prodRepo.findAll();
	}
	
	@GetMapping(path="/product/id/{id}"
			   ,produces=MediaType.APPLICATION_JSON_VALUE)
	public Product getProductById(@PathVariable(name="id") Integer pid) throws ProductNotFoundException {
		Optional<Product> optionalRes = this.prodRepo.findById(pid);
		 if (optionalRes.isPresent())
			 return optionalRes.get();
		 else
			 throw new ProductNotFoundException("Product by Id "+pid+" not found."); 
	}
	
	@GetMapping(path="/product/name/{name}"
			   ,produces=MediaType.APPLICATION_JSON_VALUE)
	public Product getProductById(@PathVariable(name="name") String prodName) throws ProductNotFoundException {
		Optional<Product> optionalRes = this.prodRepo.findByName(prodName);
		 if (optionalRes.isPresent())
			 return optionalRes.get();
		 else
			 throw new ProductNotFoundException("Product by Name "+prodName+" not found."); 
	}
	
	@ExceptionHandler
    void handleIllegalArgumentException(ProductNotFoundException prodNotFoundEx,
                      					HttpServletResponse response) 
                      							throws IOException
	{
		 // http code: 204
         response.sendError(HttpStatus.NO_CONTENT.value());
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
			getProductById(p.getPid());
			this.prodRepo.update(p);
			return false;
		} catch (ProductNotFoundException prodNotFoundEx) {
			this.prodRepo.create(p);
			return true;
		}
	}
}