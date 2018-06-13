package com.balde;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.client.RestTemplate;

import com.balde.entity.ArtistType;
import com.balde.entity.ArtistTypeShow;
import com.balde.entity.Artists;
import com.balde.entity.Localities;
import com.balde.entity.Locations;
import com.balde.entity.RepresentationUser;
import com.balde.entity.Representations;
import com.balde.entity.Roles;
import com.balde.entity.Shows;
import com.balde.entity.Types;
import com.balde.entity.Users;
import com.balde.repository.ArtistTypeShowRepository;
import com.balde.repository.ArtistsRepository;
import com.balde.repository.ArtistsTypeRepository;
import com.balde.repository.LocalitiesRepository;
import com.balde.repository.LocationsRepository;
import com.balde.repository.RepresentationUserRepository;
import com.balde.repository.RepresentationsRepository;
import com.balde.repository.RolesRepository;
import com.balde.repository.ShowsRepository;
import com.balde.repository.TypesRepository;
import com.balde.repository.UsersRepository;
import com.balde.service.api.RolesFromAPI;
import com.balde.service.api.ShowRecordesFromAPI;

@SpringBootApplication
public class ReservationSpectacleApplication implements CommandLineRunner{
	
	@Autowired
	ArtistsRepository artist;
	@Autowired
	ArtistsTypeRepository artistType;
	@Autowired
	ArtistTypeShowRepository artistTypeShow;
	@Autowired
	LocalitiesRepository localities;
	@Autowired
	LocationsRepository locations;
	@Autowired
	RepresentationsRepository representation;
	@Autowired
	RepresentationUserRepository representationUser;
	@Autowired
	RolesRepository role;
	@Autowired
	ShowsRepository show;
	@Autowired
	TypesRepository type;
	@Autowired
	UsersRepository user;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ReservationSpectacleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		// creation artist, type et artisttype
		
//		Artists a1 = new Artists("BALDE", "Ibrahima");
//		this.artist.save(a1);
//		Artists a2 = new Artists("Diallo", "Djenaba");
//		this.artist.save(a2);
//		
//		Types t1 = new Types("chanteur");
//		this.type.save(t1);
//		Types t2 = new Types("danseur");
//		this.type.save(t2);
//		Types t3 = new Types("acteur");
//		this.type.save(t3);
//		
//		ArtistType at1 = new ArtistType(a2, t1);
//		this.artistType.save(at1);
//		ArtistType at2 = new ArtistType(a1, t2);
//		this.artistType.save(at2);
//		ArtistType at3 = new ArtistType(a1, t3);
//		this.artistType.save(at3);
//		
//		// creationd de localities, location et show
//		
//		Localities l01 = new Localities("1080", "Bruxelles schaerbeek");
//		this.localities.save(l01);
//		Localities l02 = new Localities("1030", "Bruxelles saint gille");
//		this.localities.save(l02);
//		
//		Locations l11 = new Locations("zaventem", "ZA", "136 rue du marigo", l01, "", "056573");
//		this.locations.save(l11);
//		Locations l12 = new Locations("kouroula", "RD", "43 rue de touge", l02, "", "056573");
//		this.locations.save(l12);
//
//		Shows s0 = new Shows("wakanda", "Wakanda Representation", "", l11, 18, "ceci est une description",20);
//		this.show.save(s0);
//		Shows s1 = new Shows("BALFA", "balafa Representation", "", l11, 18,"ceci est une description",20);
//		this.show.save(s1);
//		Shows s2 = new Shows("sokoto", "sokoto Representation", "", l12, 18,"ceci est une description",20);
//		this.show.save(s2);
//		Shows s3 = new Shows("sogoya", "sogoya Representation", "", l12, 18,"ceci est une description",20);
//		this.show.save(s3);
//		
//		
//		
//		
//		// creation de artisteTypeShow
//		ArtistTypeShow ats0 = new ArtistTypeShow(at2, s0);
//		this.artistTypeShow.saveAndFlush(ats0);
//		ArtistTypeShow ats1 = new ArtistTypeShow(at1, s1);
//		this.artistTypeShow.saveAndFlush(ats1);
//		ArtistTypeShow ats2 = new ArtistTypeShow(at2, s2);
//		this.artistTypeShow.saveAndFlush(ats2);
//		ArtistTypeShow ats3 = new ArtistTypeShow(at1, s3);
//		this.artistTypeShow.saveAndFlush(ats3);
//		
//		//creation de representation, user et role
//		Representations r0 = new Representations(s0, l11, new Date());
//		this.representation.save(r0);
//		Representations r1 = new Representations(s1, l11, new Date());
//		this.representation.save(r1);
//		
//		Roles r11 = new Roles("ADMIN");
//		this.role.save(r11);
//		Roles r12 = new Roles("USER");
//		this.role.save(r12);
//		
//		Users u0 = new Users("balde", "balde", r11, "Alajji", "Ibrahima", "m@gmail.com", "fr");
//		this.user.save(u0);
//		
//		RepresentationUser ru0 = new RepresentationUser(r0, u0, 0);
//		this.representationUser.save(ru0);
//	
//		String url = "https://opendata.paris.fr/api/records/1.0/search//?dataset=evenements-a-paris&q=theatre&rows="+4;
//		
//		RestTemplate restTemplate = new RestTemplate();
//		
//		ShowRecordesFromAPI sr = restTemplate.getForObject(url,ShowRecordesFromAPI.class);
//		System.out.println(sr.getNhits()+" ");
//		sr.getRecords().forEach(xx -> {
//			System.out.println(xx.getFields().getAddress()+" "+xx.getFields().getCity()+" "+xx.getFields().getPlacename());
//		});
		
	}
}
