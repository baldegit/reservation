package com.balde.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "shows")
public class Shows implements Serializable{

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
	private String title;
	
	@Column(name = "poster_url",nullable = false)
	private String posterUrl;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", nullable = false)
	private Locations location;
	
	@Column(nullable = false)
	private byte bookable;
	
	@Column(nullable = false)
	private float price;
	
	@OneToMany(targetEntity = Representations.class,
			cascade = CascadeType.ALL,
			mappedBy = "show")
	private List<Representations> representation = new ArrayList<>();
	
	@OneToMany(targetEntity = ArtistTypeShow.class,
			cascade = CascadeType.ALL,
			mappedBy = "show")
	private List<ArtistTypeShow> artistType = new ArrayList<>();

	public Shows() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Shows(String slug, String title, String posterUrl, Locations location, byte bookable, float price) {
		super();
		this.slug = slug;
		this.title = title;
		this.posterUrl = posterUrl;
		this.location = location;
		this.bookable = bookable;
		this.price = price;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	public Locations getLocation() {
		return location;
	}

	public void setLocation(Locations location) {
		this.location = location;
	}

	public byte getBookable() {
		return bookable;
	}

	public void setBookable(byte bookable) {
		this.bookable = bookable;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<Representations> getRepresentation() {
		return representation;
	}

	public void setRepresentation(List<Representations> representation) {
		this.representation = representation;
	}

	public List<ArtistTypeShow> getArtistType() {
		return artistType;
	}

	public void setArtistType(List<ArtistTypeShow> artistType) {
		this.artistType = artistType;
	}
	
	
	
}
