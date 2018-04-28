package com.buy5.marketplace.model;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Top level superclass
 * 
 * @author LChawathe
 */
public abstract class Buy5Entity implements Serializable {
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	private static final long serialVersionUID = -3231453701254423285L;
	
	// Primary Key
	private Integer id;
	
	// Alternate Key
	private String name;
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer identifier) {
		this.id = identifier;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String entityName) {
		this.name = entityName;
	}
	
	@Override
	public String toString() {
		try {
			return OBJECT_MAPPER.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return super.toString();
		}
	}
}
