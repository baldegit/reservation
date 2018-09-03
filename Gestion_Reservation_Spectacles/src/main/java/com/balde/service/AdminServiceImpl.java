package com.balde.service;

import java.io.File;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor.OptimalPropertyAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.balde.api.beans.ShowFromApi;
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
	@Autowired
	private ShowsRepository showRepo;
	@Autowired
	private ArtistShowRepository artShowRepo;
	@Autowired
	private RepresentationsRepository representationRepo;
	@Autowired
	private UsersRepository userRepo;
	@Autowired
	private RolesRepository roleRepo;
	@Autowired
	private RepresentationUserRepository repUserRepo;
	
	@Autowired
	private IGestionFiles gestionFile;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

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
	public List<CheckedObject<Artists>> findArtisteChecked(int idShow) throws Exception {
		// TODO Auto-generated method stub
		List<Integer> artisteId = new ArrayList<>();
		List<Artists> artistes;
		List<CheckedObject<Artists>> CheckedObjectArray = new ArrayList<>();;
		try {
			artistes = this.artisteRepo.findAll();
			List<ArtistShow> artShow = this.artShowRepo.findArtitsteShowByShowId(idShow);
			
			artShow.forEach( as -> {
				artisteId.add(as.getArtiste().getId());
			});
		
			artistes.forEach(art ->{
				CheckedObject<Artists> tmp = new CheckedObject<>(art);
				
				CheckedObjectArray.add(tmp);
				if(artisteId.contains(art.getId()))
					tmp.setIsChecked(art.getId());
			});
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
		
		return CheckedObjectArray;
	}

	@Override
	public void saveArtiste(Artists a,Integer[] tabTypes) throws Exception {
		// TODO Auto-generated method stub
		List<ArtistType> artTypes = new ArrayList<>();
		List<Types> types;
		List<Integer> typeIds;
		try {
			typeIds = Arrays.asList(tabTypes);
			
			types = this.typeRepo.findAllById(typeIds);
			
			this.artisteRepo.save(a);
			
			types.forEach(t -> {
				
				artTypes.add(new ArtistType(a, t));
			});
			
			this.artTypeRepo.deleteArtitsteTypeByArtisteId(a.getId());
			
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
		 * Gestion des ArtisteShows ----------------------------------------------
		 * */
	@Override
	public List<ArtistShow> getAllArtistesShows() throws Exception {
		// TODO Auto-generated method stub
		try {
			return this.artShowRepo.findAll();
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
	public List<Locations> findAllLocation() throws Exception {
		// TODO Auto-generated method stub
		try {
			return this.locationRepo.findAll();
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
	//--------------------------------------------------------------------------------
	/*
	 * Gestion des Shows ----------------------------------------------
	 * */

	@Override
	public List<Object> findAllShowByPage(int page, String motCle, int size) throws Exception {
		// TODO Auto-generated method stub
		List<Object> object = new ArrayList<>();
		int [] numPage;
		try {
			
			Page<Shows> shows = this.showRepo.findShowByMotCle(motCle,PageRequest.of(page, size,Direction. ASC , "title"));
			
			numPage = new  int[shows.getTotalPages()];
			for(int i = 0; i < shows.getTotalPages(); i++) 
				numPage[i] = i;
			
			object.add(shows);
			object.add(numPage);
			
			return object;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public List<Shows> findAllShow() throws Exception {
		// TODO Auto-generated method stub
		try {
			return this.showRepo.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public Optional<Shows> findShowById(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			Optional<Shows> s = this.showRepo.findById(id);
			return s;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public void saveShow(Shows s, MultipartFile file,Integer[] tabArtistes) throws Exception {
		// TODO Auto-generated method stub
		List<ArtistShow> artShow = new ArrayList<>();
		List<Artists> artistes;
		List<Integer> artisteId;;
		try {
			
			artisteId = Arrays.asList(tabArtistes);
			artistes = this.artisteRepo.findAllById(artisteId);
			
			s.setPosterUrl(file.getOriginalFilename());
			Shows show = this.showRepo.save(s);
			this.gestionFile.saveFile(file, show.getId());
			
			artistes.forEach(a -> {
				artShow.add(new ArtistShow(a, s));
			});
			
			this.artShowRepo.deleteArtitsteShowByShowId(s.getId());
			this.artShowRepo.saveAll(artShow);
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void deleteShowById(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.showRepo.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public File getPhotoForAShow(int id) throws Exception {
		// TODO Auto-generated method stub
		return this.gestionFile.getPhotoById(id);
	}
	
	@Override
	public void updateShowFromApi(int nbRecords) throws Exception {
		// TODO Auto-generated method stub
		try {
			String url = "https://opendata.paris.fr/api/records/1.0/search//?dataset=evenements-a-paris&q=theatre&rows="+nbRecords;
			RestTemplate restTemplate = new RestTemplate();
			ShowFromApi sfapi = restTemplate.getForObject(url, ShowFromApi.class);
		
			
			sfapi.getRecords().forEach(record -> {
				Localities localite = this.localiteRepo.save(new Localities("75000", record.getFields().getCity()));
				Locations location = this.locationRepo.save(new Locations(record.getFields().getPlacename(),
						record.getFields().getPlacename(), record.getFields().getAddress(), localite, "", "+33 1 23 45 67 89"));
				
				Shows show = this.showRepo.save(new Shows(record.getFields().getTitle(), record.getFields().getTitle(),
					record.getFields().getImage(), location, 26, record.getFields().getFree_text(), 15));
			});
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	@Override
	public float updatePlace(int idShow) throws Exception {
		// TODO Auto-generated method stub
		try {
			Shows s = this.showRepo.getOne(idShow);
			s.setBookable(s.getBookable() -1);
			return s.getBookable();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}
	//--------------------------------------------------------------------------------
	/*
	 * Gestion des Represetations ----------------------------------------------
	 * */

	@Override
	public List<Object> findAllRepresentationByPage(int page,String motCle, int size) throws Exception {
		// TODO Auto-generated method stub
		List<Object> object = new ArrayList<>();
		int [] numPage;
		try {
			
			Page<Representations> representations = 
					this.representationRepo.findRepresentationByShowTitle(motCle, PageRequest.of(page, size,Direction. ASC , "show.title"));
			
			numPage = new  int[representations.getTotalPages()];
			for(int i = 0; i < representations.getTotalPages(); i++) 
				numPage[i] = i;
			
			object.add(representations);
			object.add(numPage);
			
			return object;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Representations> findAllRepresentation() throws Exception {
		// TODO Auto-generated method stub
		try {
			return this.representationRepo.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Optional<Representations> findRepresentationById(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			Optional<Representations> r = this.representationRepo.findById(id);
			return r;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Optional<Representations> saveRepresentation(Representations r) throws Exception {
		// TODO Auto-generated method stub
		try {
			Representations representation = this.representationRepo.save(r);
			return Optional.ofNullable(representation);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void deleteRepresentationById(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.representationRepo.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
	//--------------------------------------------------------------------------------
	/*
	 * Gestion des Utilisateurs ----------------------------------------------
	 * */

	@Override
	public List<Object> findAllUsersByPage(int page, String motCle, int size) throws Exception {
		// TODO Auto-generated method stub
		List<Object> object = new ArrayList<>();
		int [] numPage;
		try {
			
			Page<Users> users = this.userRepo.findUserByMotCle(motCle, PageRequest.of(page, size,Direction. ASC , "login"));
			
			numPage = new  int[users.getTotalPages()];
			for(int i = 0; i < users.getTotalPages(); i++) 
				numPage[i] = i;
			
			object.add(users);
			object.add(numPage);
			
			return object;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Optional<Users> findUserById(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			Optional<Users> u = this.userRepo.findById(id);
			return u;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public Optional<Users> findUserByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		Users u;
		try {
			u = this.userRepo.findByEmail(email);
			
			if(u instanceof Users)
				return Optional.of(u);
						
			return Optional.empty();
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Optional<Users> saveUser(Users u) throws Exception {
		// TODO Auto-generated method stub
		try {
			
			u.setPassword(bCryptPasswordEncoder.encode(u.getPassword().toString()));
			
			//Roles userRole = this.roleRepo.findRolesByRole("ADMIN");
			Roles userRole = this.roleRepo.findRolesByRole("USER");
			u.setActive(1);
			u.setRole(userRole);
			Users user = this.userRepo.save(u);
			return Optional.ofNullable(user);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void deleteUserById(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.userRepo.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
		
	}
	
	@Override
	public Users getLogedUser() throws Exception {
		// TODO Auto-generated method stub
		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String email = auth.getName();
			Users user = this.userRepo.findByEmail(email);
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	//--------------------------------------------------------------------------------
	/*
	 * Gestion des Roles ----------------------------------------------
	 * */

	@Override
	public List<Object> findAllRolesByPage(int page, String motCle, int size) throws Exception {
		// TODO Auto-generated method stub
		List<Object> object = new ArrayList<>();
		int [] numPage;
		try {
			
			Page<Roles> roles = this.roleRepo.findRolesByRole(motCle, PageRequest.of(page, size,Direction. ASC , "role"));
			
			numPage = new  int[roles.getTotalPages()];
			for(int i = 0; i < roles.getTotalPages(); i++) 
				numPage[i] = i;
			
			object.add(roles);
			object.add(numPage);
			
			return object;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Roles> findAllRole() throws Exception {
		// TODO Auto-generated method stub
		try {
			return this.roleRepo.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Optional<Roles> findRoleById(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			Optional<Roles> r = this.roleRepo.findById(id);
			return r;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Optional<Roles> saveRole(Roles r) throws Exception {
		// TODO Auto-generated method stub
		try {
			Roles role = this.roleRepo.save(r);
			return Optional.ofNullable(role);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void deleteRoleById(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.roleRepo.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}


	//--------------------------------------------------------------------------------
	/*
	 * Gestion des Reservation Utilisateurs ----------------------------------------------
	 * */
	
	@Override
	public List<RepresentationUser> getReservationOfUser() throws Exception {
		// TODO Auto-generated method stub
		List<RepresentationUser> lru;
		try {
			Users u = this.getLogedUser();
			System.out.println("@@@@@ FirstName "+u.getFirstName()+" Id "+u.getId());
			lru = this.repUserRepo.findRepresentationsUserByUserId(u.getId());
			System.out.println("@@@@@ ######### "+lru.size());
			return lru;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public void reservePlace(int idRep) throws Exception {
		// TODO Auto-generated method stub
		RepresentationUser ru;
		RepresentationUser ruTmp;
		try {
			Users u = this.getLogedUser();
			Representations r = this.representationRepo.getOne(idRep);
			ruTmp = this.repUserRepo.findRepUserByUserRepId(u.getId(), r.getId());
			if(ruTmp instanceof RepresentationUser)
				ru = ruTmp;
			else
				ru = new RepresentationUser(r, u, 0);
			
			ru.setPlaces(ru.getPlaces() + 1);
			this.repUserRepo.save(ru);
			this.updatePlace(r.getShow().getId());
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		
	}

	
	
	
}
