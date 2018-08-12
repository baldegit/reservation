package com.balde.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;


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
	
	@Column(nullable = false)
	@NotEmpty
	@Size(min = 2)
	private String slug;
	
	@Column(nullable = false)
	@NotEmpty
	@Size(min = 5)
	private String title;
	
	@Column(name = "poster_url",nullable = false)
	private String posterUrl;
	
	@ManyToOne
	@JoinColumn(name = "location_id", nullable = false)
	@Valid
	@NotNull
	private Locations location;
	
	@Column(nullable = false)
	@NotNull
	@Positive
	private int bookable;
	
	@Column(columnDefinition="TEXT")
	@NotEmpty
	private String description;
	
	@Column(nullable = false)
	@NotNull
	@Positive
	private float price;
	
	@OneToMany(targetEntity = Representations.class,
			cascade = CascadeType.ALL,
			mappedBy = "show")
	private List<Representations> representation = new ArrayList<>();
	
	@OneToMany(targetEntity = ArtistShow.class,
			cascade = CascadeType.ALL,
			mappedBy = "show")
	private List<ArtistShow> artistShow = new ArrayList<>();

	public Shows() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Shows(String slug, String title, String posterUrl, Locations location, int bookable, String description,
			float price) {
		super();
		this.slug = slug;
		this.title = title;
		this.posterUrl = posterUrl;
		this.location = location;
		this.bookable = bookable;
		this.description = description;
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

	public int getBookable() {
		return bookable;
	}

	public void setBookable(int bookable) {
		this.bookable = bookable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<ArtistShow> getArtistType() {
		return artistShow;
	}

	public void setArtistType(List<ArtistShow> artistShow) {
		this.artistShow = artistShow;
	}
	
}
