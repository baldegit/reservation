package com.balde.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.balde.entity.*;
import com.balde.service.IAdminService;

@Controller
@RequestMapping("/admin/location")
public class AdminLocationController {

	private static final int PAGES_SIZE = 5;
	
	private List<Localities> localite;
	
	@Autowired
	private IAdminService service;
	
	@GetMapping(value = {"/","location"})
	public String goToTypePage(Model model,@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "motCle", required = false, defaultValue="") String adresse) throws Exception  {
		
		int [] numPage;
		Page<Locations> locations;
		
		try {
			List<Object> object = this.service.findAllLocationByPage(page,adresse, this.PAGES_SIZE);
			
			locations = (Page<Locations>) object.get(0);
			numPage = (int []) object.get(1);
			
			model.addAttribute("motCle", adresse);
			model.addAttribute("locations", locations);
			model.addAttribute("numPage", numPage);
			model.addAttribute("pageCourante", page);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return "adminTemplates/adminLocation";
	}
	
	@GetMapping("/editOrCreate")
	public String editOrCreateType(Model model, @RequestParam(name="locationId", required = false, defaultValue = "-1") int id) throws Exception { 
		
		Optional<Locations> optLocation; 
		Locations location;
		
		
		try {
			optLocation = this.service.findLocationById(id);
			localite = this.service.findAllLocalite();
			if(optLocation.isPresent())
				location = optLocation.get();
			else
				location = new Locations();
			
			model.addAttribute("location", location);
			model.addAttribute("localite", localite);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return "adminTemplates/adminLocationForm";
	}
	
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdateType(Model model,@Valid @ModelAttribute("location") Locations location, BindingResult result) throws Exception{
		
		try {
			if(result.hasErrors()) {
				model.addAttribute("localite", localite);
				return "adminTemplates/adminLocationForm";
				
			}
			
			this.service.saveLocation(location);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/admin/location/";
	}
	
	@GetMapping("/delete")
	public String deleteType(@RequestParam(name="locationId") int id) throws Exception{
		
		try {
			this.service.deleteLocationById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "redirect:/admin/location/";
	}
}
