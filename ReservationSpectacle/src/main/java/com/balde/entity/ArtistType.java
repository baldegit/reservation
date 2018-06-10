package com.balde.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;

@Entity
@Table(name = "artist_type")
public class ArtistType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "artist_id", nullable = false)
	private Artists artist;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "type_id", nullable = false)
	private Types type;
	
	@OneToMany(targetEntity = ArtistTypeShow.class,
				cascade = CascadeType.ALL,
				mappedBy = "artistType")
	private List<ArtistTypeShow> artistTypeShow = new ArrayList<>();

	public ArtistType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArtistType(Artists artist, Types type) {
		super();
		this.artist = artist;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Artists getArtist() {
		return artist;
	}

	public void setArtist(Artists artist) {
		this.artist = artist;
	}

	public Types getType() {
		return type;
	}

	public void setType(Types type) {
		this.type = type;
	}

	public List<ArtistTypeShow> getArtistTypeShow() {
		return artistTypeShow;
	}

	public void setArtistTypeShow(List<ArtistTypeShow> artistTypeShow) {
		this.artistTypeShow = artistTypeShow;
	}

	
}
