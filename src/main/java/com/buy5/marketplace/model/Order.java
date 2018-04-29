package com.buy5.marketplace.model;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"order_id",
					"user_name",
					"product_name",
					"order_desc",
					"quantity",
					"total_price",
					"order_date",
					"success"
   				   })
public class Order extends Buy5Entity {
	
	private static final long serialVersionUID = -1941404395162694889L;

	private static final AtomicInteger ORDER_ID_GENERATOR = new AtomicInteger();
	
	private String userName;
	private String productName;
	private int quantity;
	private int totalPrice;
	private Date orderDate;
	private boolean success = false;
	
	public Order () {
		this("ghost", "dummy", 0, 0);
	}
	
	public Order (String userName,
			  	  String productName,
			      int quantity)
	{
		this(userName, productName, quantity, 0);
	}
	
	public Order (String userName,
				  String productName,
				  int quantity,
				  int totalPrice)
	{
		synchronized (ORDER_ID_GENERATOR) {
			this.setId(ORDER_ID_GENERATOR.incrementAndGet());
			super.setName(userName + "." + productName);
			
			this.setOrderDate(new Date());
		}
		
		setUserName(userName);
		setProductName(productName);
		
		setQuantity(quantity);
		setTotalPrice(totalPrice);
	}
	
	
	@JsonProperty("order_id")
	public Integer getId() {
		return super.getId();
	}
	
	@JsonProperty("order_desc")
	public String getName() {
		return super.getName();
	}
	
	@JsonProperty("user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String uname) {
		this.userName = uname;
	}
	
	@JsonProperty("product_name")
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@JsonProperty("total_price")
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@JsonProperty("order_date")
	public Date getOrderDate() {
		return orderDate;
	}
	private void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
