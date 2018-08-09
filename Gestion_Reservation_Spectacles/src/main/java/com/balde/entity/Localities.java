package com.balde.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "localities")
public class Localities implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "postal_code",nullable = false)
	@NotEmpty
	@Size(min = 4)
	private String postalCode;
	
	@Column(nullable = false)
	@NotEmpty
	private String locality;
	
	@OneToMany(targetEntity = Locations.class,
			cascade = CascadeType.ALL,
			mappedBy = "locality")
	private List<Locations> location = new ArrayList<>();

	public Localities() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Localities(String postalCode, String locality) {
		super();
		this.postalCode = postalCode;
		this.locality = locality;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public List<Locations> getLocation() {
		return location;
	}

	public void setLocation(List<Locations> location) {
		this.location = location;
	}

	
	
}
