package com.balde.service.api;

import java.util.ArrayList;
import java.util.List;

import com.balde.entity.Roles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class RolesFromAPI {
	
	@JsonProperty(value = "")
	List<Roles> roles = new ArrayList<>();
	
	public RolesFromAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RolesFromAPI(List<Roles> roles) {
		super();
		this.roles = roles;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
	
	
	
//	public RolesFromAPI(int id, String roles) {
//		super();
//		this.id = id;
//		this.roles = roles;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getRoles() {
//		return roles;
//	}
//
//	public void setRoles(String roles) {
//		this.roles = roles;
//	}
	
	
}
