package com.balde.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "representations")
public class Representations implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "show_id", nullable = false)
	@NotNull
	@Valid
	private Shows show;
	
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Locations location;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	@FutureOrPresent
	private Date date;
	
	@OneToMany(targetEntity = RepresentationUser.class,
			mappedBy = "representation",
			cascade = CascadeType.ALL)
	private List<RepresentationUser> representationUser = new ArrayList<>();

	public Representations() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Representations(Shows show, Locations location, Date date) {
		super();
		this.show = show;
		this.location = location;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Shows getShow() {
		return show;
	}

	public void setShow(Shows show) {
		this.show = show;
	}

	public Locations getLocation() {
		return location;
	}

	public void setLocation(Locations location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<RepresentationUser> getRepresentationUser() {
		return representationUser;
	}

	public void setRepresentationUser(List<RepresentationUser> representationUser) {
		this.representationUser = representationUser;
	}
	
}
