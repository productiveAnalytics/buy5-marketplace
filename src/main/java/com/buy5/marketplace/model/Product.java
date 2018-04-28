package com.buy5.marketplace.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonPropertyOrder({"pid",
					"name",
					"display_name",
					"price",
					"inventory",
					"cat_id"
				   })
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1728011471795833871L;
	
	// Primary Key
	private Integer pid;
	
	private String name;
	private String description;
	private int inventory;
	private int price;
	
	// Foreign Key to Category
	private Integer categoryId = null;
	
	public Product() {}
	
	public Product(Integer productId,
				   String name,
				   String displayName,
				   int inventory,
				   int price) {
		this(productId, name, displayName, inventory, price, null);
	}
	
	public Product(Integer productId,
				   String name,
				   String displayName,
				   int inventory,
				   int price,
				   Integer categoryId) {
		this.pid = productId;
		this.name = name;
		this.description = displayName;
		this.inventory = inventory;
		this.price = price;
		if (categoryId > 0) this.categoryId = categoryId;
	}

	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("display_name")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	@JsonProperty("cat_id")
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	@Override
	public String toString() {
		try {
			return (new ObjectMapper()).writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return super.toString();
		}
	}
}
