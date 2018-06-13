package com.balde.service;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.balde.entity.*;
import com.balde.service.api.RolesFromAPI;


public interface ICatalogueService {
	// Artiste
	public Optional<Page<Artists>> getArtistesByPage(Pageable pageable);
	public Optional<?> deleteArtisteById(int id);
	public Optional<Artists> getArtisteById(int id);
	public Optional<?> updateArtiste(Artists artiste,Types type);
	public Optional<ArtistType> saveArtiste(Artists artiste,Types type);
	public Optional<Artists> saveArtiste(Artists artiste) throws Exception;
	
	// Type
	
	public Optional<Page<Shows>> getAllShows(Pageable pageable) throws Exception;
	public Optional<?> deleteShows(int id) throws Exception;
	public Optional<Shows> getShowsById(int id) throws Exception;
	public Optional<?> updateShowFromApi(int nbRecords) throws Exception;
	
	// ArtistesTypes
	public Optional<List<ArtistType>> getArtistesTypes();
	
	//Roles
	public Optional<RolesFromAPI> getRolesFromAPI() throws Exception;// my home made API
	
	//Reservation
	public boolean isPlace(Shows s);
	public void updatePlace(int id);
	
	
}
