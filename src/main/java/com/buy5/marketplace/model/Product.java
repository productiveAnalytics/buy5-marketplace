package com.buy5.marketplace.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"pid",
					"name",
					"display_name",
					"price",
					"inventory",
					"cat_id"
				   })
public final class Product extends Buy5Entity {

	private static final long serialVersionUID = -1475617802178540346L;

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
		super.setId(productId);
		super.setName(name);
		
		this.description = displayName;
		this.inventory = inventory;
		this.price = price;
		if (categoryId > 0) this.categoryId = categoryId;
	}

	@JsonProperty("pid")
	public Integer getId() {
		return super.getId();
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
}
