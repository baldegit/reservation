package com.balde.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.balde.entity.*;
import com.balde.repository.*;
import com.balde.service.api.Role;
import com.balde.service.api.RolesFromAPI;
import com.balde.service.api.ShowRecordesFromAPI;

@Service
@Transactional
public class CatalogueServiceImpl implements ICatalogueService{
	
	@Autowired
	ArtistsRepository artisteRepo;
	@Autowired
	TypesRepository typeRepo;
	@Autowired
	ArtistsTypeRepository artTypeRepos;
	@Autowired
	ShowsRepository showRepo;
	@Autowired
	LocalitiesRepository localitieRepo;
	@Autowired
	LocationsRepository locationRepo;
	
	@Override
	public Optional<Page<Artists>> getArtistesByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Artists> pA = null;
		try {
			pA = this.artisteRepo.findAll(pageable);
			return Optional.ofNullable(pA);
		} catch (Exception e) {
			// TODO: handle exception
			return Optional.empty();
		}
	}

	@Override
	public Optional<?> deleteArtisteById(int id) {
		// TODO Auto-generated method stub
		
		try {
			if(this.artisteRepo.findById(id).isPresent()) {
				this.artisteRepo.deleteById(id);
				return Optional.of(1);
			}
			return Optional.empty();
		} catch (Exception e) {
			// TODO: handle exception
			return Optional.empty();
		}
		
	}

	@Override
	public Optional<?> updateArtiste(Artists artiste, Types type) {
		try {
			if(this.artisteRepo.findById(artiste.getId()).isPresent() &&
					this.typeRepo.findById(type.getId()).isPresent()) {
				this.typeRepo.saveAndFlush(type);
				this.artisteRepo.saveAndFlush(artiste);
				return Optional.of(1);
			}
		} catch (Exception e) {
			return Optional.empty();
		}
		return Optional.empty();
	}

	@Override
	public Optional<ArtistType> saveArtiste(Artists artiste, Types type) {
		// TODO Auto-generated method stub
		try {
			Artists a = this.artisteRepo.save(artiste);
			Types t = this.typeRepo.save(type);
			ArtistType artTyp = this.artTypeRepos.save(new ArtistType(a, t));
			return Optional.ofNullable(artTyp);
		} catch (Exception e) {
			// TODO: handle exception
			return Optional.empty();
		}
	}

	@Override
	public Optional<List<ArtistType>> getArtistesTypes() {
		// TODO Auto-generated method stub
		
		try {
			return Optional.ofNullable(this.artTypeRepos.findAll());
		} catch (Exception e) {
			// TODO: handle exception
			return Optional.empty();
		}
	}

	@Override
	public Optional<Artists> getArtisteById(int id) {
		// TODO Auto-generated method stub
		try {
			return this.artisteRepo.findById(id);
			
		} catch (Exception e) {
			// TODO: handle exception
			return Optional.empty();
		}
	}

	@Override
	public Optional<Artists> saveArtiste(Artists artiste) throws Exception {
		// TODO Auto-generated method stub
		Artists a;
	
		try {
			a = this.artisteRepo.save(artiste);
			return Optional.ofNullable(a);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		
	}

	@Override
	public Optional<Page<Shows>> getAllShows(Pageable pageable) throws Exception {
		// TODO Auto-generated method stub
		Page<Shows> pS = null;
		try {
			pS = this.showRepo.findAll(pageable);
			return Optional.ofNullable(pS);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public Optional<?> deleteShows(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			if(this.showRepo.findById(id).isPresent()) {
				this.showRepo.deleteById(id);
				return Optional.of(1);
			}
			return Optional.empty();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public Optional<Shows> getShowsById(int id) throws Exception {
		try {
			return this.showRepo.findById(id);
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public Optional<?> updateShowFromApi(int nbRecords) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		try {
			String url = "https://opendata.paris.fr/api/records/1.0/search//?dataset=evenements-a-paris&q=theatre&rows="+nbRecords;
			RestTemplate restTemplate = new RestTemplate();
			ShowRecordesFromAPI sr = restTemplate.getForObject(url,ShowRecordesFromAPI.class);
			
			sr.getRecords().forEach(xx -> {
				 
				Localities localitie = this.localitieRepo.save(new Localities("1989",xx.getFields().getCity()));
				Locations location = this.locationRepo.save(
								new Locations(xx.getFields().getPlacename(),
								xx.getFields().getPlacename(), xx.getFields().getAddress(),
								localitie,"" ,"0000000")
						);
				
				
				Shows show = this.showRepo.save(
							new Shows(xx.getFields().getTitle(), xx.getFields().getTitle(),
							xx.getFields().getImage(), location, 20, xx.getFields().getFree_text(),14)
						);
			});
			
			
			return Optional.ofNullable(1);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public Optional<RolesFromAPI> getRolesFromAPI() throws Exception {
		// TODO Auto-generated method stub
		
		String url = "http://localhost:8080/ReservationSpectacle/roles/";
		try {
		
			RestTemplate restTemplate = new RestTemplate();
			RolesFromAPI roles = restTemplate.getForObject(url,RolesFromAPI.class);
			
			return Optional.ofNullable(roles);
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public boolean isPlace(Shows s) {
		// TODO Auto-generated method stub
		int nbPlace = s.getBookable();
		
		return nbPlace > 0 ? true : false;
	}

	@Override
	public void updatePlace(int id) {
		// TODO Auto-generated method stub
		try {
			Shows s = this.getShowsById(id).get();
			s.setBookable(s.getBookable() - 1);
			this.showRepo.saveAndFlush(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



}
