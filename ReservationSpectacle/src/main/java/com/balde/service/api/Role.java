package com.balde.service.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Role {
	
	private int id;
	private String role;
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
}
