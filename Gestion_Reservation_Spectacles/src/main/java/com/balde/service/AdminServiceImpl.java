package com.balde.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.balde.beans.CheckedObject;
import com.balde.entity.*;
import com.balde.repository.*;

@Service
@Transactional()
public class AdminServiceImpl implements IAdminService{
	
	@Autowired
	private TypesRepository typeRepo;
	@Autowired
	private ArtistsRepository artisteRepo;
	@Autowired
	private ArtistsTypeRepository artTypeRepo;
	@Autowired
	private LocalitiesRepository localiteRepo;
	@Autowired
	private LocationsRepository locationRepo;

	public AdminServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Gestion du type d'artiste ----------------------------------------------
	 * */
	//--------------------------------------------------------------------------------
	@Override
	public List<Object> findAllTypesByPage(int page,String motCle,int size) throws Exception {
		// TODO Auto-generated method stub
		List<Object> object = new ArrayList<>();
		int [] numPage;
		try {
			
			Page<Types> types = this.typeRepo.findTypeByMotCle(motCle,PageRequest.of(page, size,Direction. ASC , "type"));
			
			numPage = new  int[types.getTotalPages()];
			for(int i = 0; i < types.getTotalPages(); i++) 
				numPage[i] = i;
			
			object.add(types);
			object.add(numPage);
			
			return object;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public List<Types> findAllTypes() throws Exception {
		// TODO Auto-generated method stub
		try {
			return this.typeRepo.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public List<CheckedObject<Types>> findTypesChecked(int idArtiste) throws Exception {
		// TODO Auto-generated method stub
		List<Integer> typesId = new ArrayList<>();
		List<Types> types;
		List<CheckedObject<Types>> CheckedObjectArray = new ArrayList<>();;
		try {
			types = this.typeRepo.findAll();
			List<ArtistType> artType = this.artTypeRepo.findArtitsteTypeByArtisteId(idArtiste);
			
			artType.forEach( at -> {
				typesId.add(at.getType().getId());
			});
			
			types.forEach(type ->{
				CheckedObject<Types> tmp = new CheckedObject<Types>(type);
				CheckedObjectArray.add(tmp);
				if(typesId.contains(type.getId()))
					tmp.setIsChecked(type.getId());
			});
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
		
		return CheckedObjectArray;
	}

	@Override
	public Optional<Types> findTypeById(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			Optional<Types> t = this.typeRepo.findById(id);
			return t;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Optional<Types> saveType(Types t) throws Exception {
		// TODO Auto-generated method stub
		try {
			Types type = this.typeRepo.save(t);
			return Optional.ofNullable(type);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void deleteTypeById(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.typeRepo.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
	
	//--------------------------------------------------------------------------------
	/*
	 * Gestion des Artistes ----------------------------------------------
	 * */
	@Override
	public List<Object> findAllArtisteByPage(int page, String motCle, int size) throws Exception {
		// TODO Auto-generated method stub
		List<Object> object = new ArrayList<>();
		int [] numPage;
		try {
			
			Page<Artists> artiste = this.artisteRepo.findArtisteByMotCle(motCle,PageRequest.of(page, size,Direction. ASC , "firstName"));
			
			numPage = new  int[artiste.getTotalPages()];
			for(int i = 0; i < artiste.getTotalPages(); i++) 
				numPage[i] = i;
			
			object.add(artiste);
			object.add(numPage);
			
			return object;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Optional<Artists> findArtisteById(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			Optional<Artists> a = this.artisteRepo.findById(id);
			return a;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void saveArtiste(Artists a,Integer[] tabTypes) throws Exception {
		// TODO Auto-generated method stub
		List<ArtistType> artTypes = new ArrayList<>();
		List<Types> types;
		List<Integer> typeIds;
		try {
			System.out.println("@ ID "+a.getId());
			typeIds = Arrays.asList(tabTypes);
			
			types = this.typeRepo.findAllById(typeIds);
			
			this.artisteRepo.save(a);
			
			types.forEach(t -> {
				
				artTypes.add(new ArtistType(a, t));
			});
			
			this.artTypeRepo.deleteArtitsteTypeByArtisteId(a.getId());
			System.out.println("@ ID "+a.getId());
			
			this.artTypeRepo.saveAll(artTypes);
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void deleteArtisteById(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.artisteRepo.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
		
	}
	
	// ArtistesTypes ------------------------------------------------------------
	@Override
	public List<ArtistType> getAllArtistesTypes() throws Exception {
		// TODO Auto-generated method stub
		try {
			return this.artTypeRepo.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
		
	}

	@Override
	public List<ArtistType> getArtistesByArtisteId(int idArtiste) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			return this.artTypeRepo.findArtitsteTypeByArtisteId(idArtiste);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
	//--------------------------------------------------------------------------------
		/*
		 * Gestion des Localites ----------------------------------------------
		 * */

	@Override
	public List<Object> findAllLocaliteByPage(int page, String motCle, int size) throws Exception {
		// TODO Auto-generated method stub
		List<Object> object = new ArrayList<>();
		int [] numPage;
		try {
			
			Page<Localities> localites = this.localiteRepo.findLocaliteByCodePostal(motCle,PageRequest.of(page, size,Direction. ASC , "postalCode"));
			
			numPage = new  int[localites.getTotalPages()];
			for(int i = 0; i < localites.getTotalPages(); i++) 
				numPage[i] = i;
			
			object.add(localites);
			object.add(numPage);
			
			return object;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public List<Localities> findAllLocalite() throws Exception {
		// TODO Auto-generated method stub
		try {
			return this.localiteRepo.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Optional<Localities> findLocaliteById(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			Optional<Localities> l = this.localiteRepo.findById(id);
			return l;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Optional<Localities> saveLocalite(Localities l) throws Exception {
		// TODO Auto-generated method stub
		try {
			Localities localite = this.localiteRepo.save(l);
			return Optional.ofNullable(localite);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void deleteLocaliteById(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.localiteRepo.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
		
	}
	//--------------------------------------------------------------------------------
			/*
			 * Gestion des Location ----------------------------------------------
			 * */

	@Override
	public List<Object> findAllLocationByPage(int page, String motCle, int size) throws Exception {
		// TODO Auto-generated method stub
		List<Object> object = new ArrayList<>();
		int [] numPage;
		try {
			
			Page<Locations> locations = this.locationRepo.findLocationByAdresse(motCle,PageRequest.of(page, size,Direction. ASC , "address"));
			
			numPage = new  int[locations.getTotalPages()];
			for(int i = 0; i < locations.getTotalPages(); i++) 
				numPage[i] = i;
			
			object.add(locations);
			object.add(numPage);
			
			return object;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Optional<Locations> findLocationById(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			Optional<Locations> l = this.locationRepo.findById(id);
			return l;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Optional<Locations> saveLocation(Locations l) throws Exception {
		// TODO Auto-generated method stub
		try {
			Locations location = this.locationRepo.save(l);
			return Optional.ofNullable(location);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void deleteLocationById(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.locationRepo.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
	

}
