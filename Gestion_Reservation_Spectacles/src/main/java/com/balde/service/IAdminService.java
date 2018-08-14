package com.balde.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

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
	public List<CheckedObject<Artists>> findArtisteChecked(int idShow) throws Exception;
	public void saveArtiste(Artists a,Integer[] types) throws Exception;
	public void deleteArtisteById(int id) throws Exception;
	
	// ArtistesTypes
	public List<ArtistType> getAllArtistesTypes()throws Exception;
	public List<ArtistType> getArtistesByArtisteId(int idArtiste)throws Exception;
	
	// ArtistesShows
	public List<ArtistShow> getAllArtistesShows()throws Exception;
	
	// Localite
	public List<Object> findAllLocaliteByPage(int page,String motCle,int size) throws Exception;
	public List<Localities> findAllLocalite() throws Exception;
	public Optional<Localities> findLocaliteById(int id) throws Exception;
	public Optional<Localities> saveLocalite(Localities l) throws Exception;
	public void deleteLocaliteById(int id) throws Exception;
	
	// Location
	public List<Object> findAllLocationByPage(int page,String motCle,int size) throws Exception;
	public List<Locations> findAllLocation() throws Exception;
	public Optional<Locations> findLocationById(int id) throws Exception;
	public Optional<Locations> saveLocation(Locations l) throws Exception;
	public void deleteLocationById(int id) throws Exception;
	
	// Shows
	public List<Object> findAllShowByPage(int page,String motCle,int size) throws Exception;
	public List<Shows> findAllShow() throws Exception;
	public Optional<Shows> findShowById(int id) throws Exception;
	public void saveShow(Shows s, MultipartFile file,Integer[] artistes ) throws Exception;
	public void deleteShowById(int id) throws Exception;
	public File getPhotoForAShow(int id) throws Exception;
	public void updateShowFromApi(int nbRecords) throws Exception;
	
	// Representation
	public List<Object> findAllRepresentationByPage(int page,String motCle,int size) throws Exception;
	public List<Representations> findAllRepresentation() throws Exception;
	public Optional<Representations> findRepresentationById(int id) throws Exception;
	public Optional<Representations> saveRepresentation(Representations r) throws Exception;
	public void deleteRepresentationById(int id) throws Exception;
	
	// Users
	public List<Object> findAllUsersByPage(int page,String motCle,int size) throws Exception;
	public Optional<Users> findUserById(int id) throws Exception;
	public Optional<Users> saveUser(Users u) throws Exception;
	public void deleteUserById(int id) throws Exception;
	
	// Roles
	public List<Object> findAllRolesByPage(int page,String motCle,int size) throws Exception;
	public List<Roles> findAllRole() throws Exception;
	public Optional<Roles> findRoleById(int id) throws Exception;
	public Optional<Roles> saveRole(Roles r) throws Exception;
	public void deleteRoleById(int id) throws Exception;
	
}
