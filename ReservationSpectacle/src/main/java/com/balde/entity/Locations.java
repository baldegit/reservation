package com.balde.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class Locations implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true)
	private String slug;
	
	@Column(nullable = false)
	private String designation;
	
	@Column(nullable = false)
	private String address;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "locality_id", nullable = false)
	private Localities locality;
	
	@Column(name = "wabsite",nullable = false)
	private String webSite;
	
	@Column(nullable = false)
	private String phone;
	
	@OneToMany(targetEntity = Shows.class,
			cascade = CascadeType.ALL,
			mappedBy = "location")
	private List<Shows> show = new ArrayList<>();
	
	@OneToMany(targetEntity = Representations.class,
			cascade = CascadeType.ALL,
			mappedBy = "location")
	private List<Representations> representation = new ArrayList<>();

	public Locations() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Locations(String slug, String designation, String address, Localities locality, String webSite,
			String phone) {
		super();
		this.slug = slug;
		this.designation = designation;
		this.address = address;
		this.locality = locality;
		this.webSite = webSite;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public Localities getLocality() {
		return locality;
	}

	public void setLocality(Localities locality) {
		this.locality = locality;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Shows> getShow() {
		return show;
	}

	public void setShow(List<Shows> show) {
		this.show = show;
	}

	public List<Representations> getRepresentation() {
		return representation;
	}

	public void setRepresentation(List<Representations> representation) {
		this.representation = representation;
	}
	
}
