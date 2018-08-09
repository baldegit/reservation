package com.balde.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.balde.beans.CheckedObject;
import com.balde.entity.*;

public interface IAdminService {
	
	// Types
	public List<Object> findAllTypesByPage(int page,String motCle,int size) throws Exception;
	public List<Types> findAllTypes() throws Exception;
	public List<CheckedObject<Types>> findTypesChecked(int idArtiste) throws Exception;
	public Optional<Types> findTypeById(int id) throws Exception;
	public Optional<Types> saveType(Types t) throws Exception;
	public void deleteTypeById(int id) throws Exception;
	
	//Arttiste
	public List<Object> findAllArtisteByPage(int page,String motCle,int size) throws Exception;
	public Optional<Artists> findArtisteById(int id) throws Exception;
	public void saveArtiste(Artists a,Integer[] types) throws Exception;
	public void deleteArtisteById(int id) throws Exception;
	
	// ArtistesTypes
	public List<ArtistType> getAllArtistesTypes()throws Exception;
	public List<ArtistType> getArtistesByArtisteId(int idArtiste)throws Exception;
	
	// Localite
	public List<Object> findAllLocaliteByPage(int page,String motCle,int size) throws Exception;
	public List<Localities> findAllLocalite() throws Exception;
	public Optional<Localities> findLocaliteById(int id) throws Exception;
	public Optional<Localities> saveLocalite(Localities l) throws Exception;
	public void deleteLocaliteById(int id) throws Exception;
	
	// Location
	public List<Object> findAllLocationByPage(int page,String motCle,int size) throws Exception;
	public Optional<Locations> findLocationById(int id) throws Exception;
	public Optional<Locations> saveLocation(Locations l) throws Exception;
	public void deleteLocationById(int id) throws Exception;
	
	
}
