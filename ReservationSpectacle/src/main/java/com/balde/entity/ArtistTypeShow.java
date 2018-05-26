package com.balde.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name ="artist_type_show")
public class ArtistTypeShow implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "artist_type_id", nullable = false)
	private ArtistType artistType;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "show_id", nullable = false)
	private Shows show;

	public ArtistTypeShow() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArtistTypeShow(ArtistType artistType, Shows show) {
		super();
		this.artistType = artistType;
		this.show = show;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArtistType getArtistType() {
		return artistType;
	}

	public void setArtistType(ArtistType artistType) {
		this.artistType = artistType;
	}

	public Shows getShow() {
		return show;
	}

	public void setShow(Shows show) {
		this.show = show;
	}
	
}
