package com.buy5.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"person_id",
					"person_name",
					"user_name"
				   })
@JsonIgnoreProperties(value = { "id" })
public final class Person extends Buy5Entity {

	private static final long serialVersionUID = -6921279260802679262L;
	
	private String userName;
	
	public Person (Integer personId
				  ,String personName
				  ,String userName)
	{
		super.setId(personId);
		super.setName(personName);
		this.userName = userName;
	}
	
	@JsonProperty("person_id")
	public Integer getPers_id() {
		return super.getId();	// Delegate to super
	}
	public void setPers_id(Integer pers_id) {
		super.setId(pers_id);
	}
	
	@JsonProperty("person_name")
	public String getName() {
		return super.getName();
	}
	
	@JsonProperty("user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}