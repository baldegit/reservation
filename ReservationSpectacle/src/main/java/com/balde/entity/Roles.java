package com.balde.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "roles")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Roles implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private int id;
	
	@Column(nullable = false, unique = true)
	private String role;
	
	@OneToMany(targetEntity = Users.class,
			cascade = CascadeType.ALL,
			mappedBy = "role")
	private List<Users> user = new ArrayList<>();

	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Roles(String role) {
		super();
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

//	public List<Users> getUser() {
//		return user;
//	}

	public void setUser(List<Users> user) {
		this.user = user;
	}
	
}
