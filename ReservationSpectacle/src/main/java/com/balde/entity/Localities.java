package com.balde.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mysql.fabric.xmlrpc.base.Array;

@Entity
@Table(name = "localities")
public class Localities implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "postal_code",nullable = false)
	private String PostalCode;
	
	@Column(nullable = false)
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
		PostalCode = postalCode;
		this.locality = locality;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPostalCode() {
		return PostalCode;
	}

	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
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
