package com.balde.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class Artists implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "firtsname",nullable = false)
	private String firstName;
	
	@Column(name = "lastsname",nullable = false)
	private String lastName;
	
	@OneToMany(targetEntity = ArtistType.class,
				mappedBy = "artist",
				cascade = CascadeType.ALL)
	private List<ArtistType> artistType = new ArrayList<>();
	
	public Artists() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Artists(String firstName, String lastName, List<ArtistType> artistType) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.artistType = artistType;
	}
	
	

	public Artists(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<ArtistType> getArtisteType() {
		return artistType;
	}

	public void setArtisteType(List<ArtistType> artistType) {
		this.artistType = artistType;
	}
	
}
