package com.balde.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "types")
public class Types implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String type;
	@OneToMany(targetEntity = ArtistType.class,
				mappedBy = "type",
				cascade = CascadeType.ALL)
	private List<ArtistType> artistType = new ArrayList<>();
	public Types() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Types(String type, List<ArtistType> artistType) {
		super();
		this.type = type;
		this.artistType = artistType;
	}
	public Types(String type) {
		super();
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<ArtistType> getArtisteType() {
		return artistType;
	}
	public void setArtisteType(List<ArtistType> artistType) {
		this.artistType = artistType;
	}
	
	
}
