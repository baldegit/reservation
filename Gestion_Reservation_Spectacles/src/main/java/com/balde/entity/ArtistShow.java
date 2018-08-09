package com.balde.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name ="artist_type_show")
public class ArtistShow implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "artist_id", nullable = false)
	private Artists artiste;
	
	@ManyToOne
	@JoinColumn(name = "show_id", nullable = false)
	private Shows show;

	public ArtistShow() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArtistShow(Artists artistType, Shows show) {
		super();
		this.artiste = artistType;
		this.show = show;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Artists getArtiste() {
		return artiste;
	}

	public void setArtiste(Artists artiste) {
		this.artiste = artiste;
	}

	public Shows getShow() {
		return show;
	}

	public void setShow(Shows show) {
		this.show = show;
	}
	
}
