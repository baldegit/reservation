package com.balde.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name ="representation_user")
public class RepresentationUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "representation_id", nullable = false)
	private Representations representation;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Users user;
	
	@Column(nullable = false)
	private int places;

	public RepresentationUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RepresentationUser(Representations representation, Users user, int places) {
		super();
		this.representation = representation;
		this.user = user;
		this.places = places;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Representations getRepresentation() {
		return representation;
	}

	public void setRepresentation(Representations representation) {
		this.representation = representation;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getPlaces() {
		return places;
	}

	public void setPlaces(int places) {
		this.places = places;
	}
	
}
