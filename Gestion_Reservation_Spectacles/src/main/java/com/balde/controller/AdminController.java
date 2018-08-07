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

import com.balde.beans.CheckedObject;
import com.balde.entity.ArtistType;
import com.balde.entity.Artists;
import com.balde.entity.Types;
import com.balde.service.IAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static final int PAGES_SIZE = 5;
	
	private List<Types> types;
	private List<CheckedObject<Types>> checkedObjectArray;
	
	@Autowired
	private IAdminService service;
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "adminDashoard";
	}
	
	// Types D'artiste -----------------------------------------------------------------------------------
	
	@GetMapping("/type")
	public String goToTypePage(Model model,@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "motCle", required = false, defaultValue="") String motCle) throws Exception  {
		
		int [] numPage;
		Page<Types> types;
		
		try {
			List<Object> object = this.service.findAllTypesByPage(page,motCle, this.PAGES_SIZE);
			
			types = (Page<Types>) object.get(0);
			numPage = (int []) object.get(1);
			
			model.addAttribute("motCle", motCle);
			model.addAttribute("types", types);
			model.addAttribute("numPage", numPage);
			model.addAttribute("pageCourante", page);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return "adminType";
	}
	
	@GetMapping("/editOrCreateType")
	public String editOrCreateType(Model model, @RequestParam(name="typeId", required = false, defaultValue = "-1") int typeId) throws Exception { 
		
		Optional<Types> opType; 
		Types type;
		
		try {
			opType = this.service.findTypeById(typeId);
			if(opType.isPresent())
				type = opType.get();
			else
				type = new Types();
			
			model.addAttribute("types", type);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return "adminTypeForm";
	}
	
	@PostMapping("/saveOrUpdateType")
	public String saveOrUpdateType(Model model,@Valid Types type, BindingResult result) throws Exception{
		
		try {
			if(result.hasErrors())
				return "adminTypeForm";
			
			this.service.saveType(type);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/admin/type";
	}
	
	@GetMapping("/deleteType")
	public String deleteType(@RequestParam(name="typeId") int id) throws Exception{
		
		try {
			this.service.deleteTypeById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "redirect:/admin/type";
	}
	
	// Artistes -----------------------------------------------------------------------------------
	
	@GetMapping("/artiste")
	public String goToArtistePage(Model model,@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "motCle", required = false, defaultValue="") String motCle) throws Exception  {
		
		int [] numPage;
		Page<Artists> artiste;
		List<ArtistType> artType;
		
		try {
			List<Object> object = this.service.findAllArtisteByPage(page,motCle, this.PAGES_SIZE);
			
			artiste = (Page<Artists>) object.get(0);
		
			numPage = (int []) object.get(1);
			
			artType = this.service.getAllArtistesTypes();
			model.addAttribute("motCle", motCle);
			model.addAttribute("artisteType", artType);
			model.addAttribute("artistes", artiste);
			model.addAttribute("numPage", numPage);
			model.addAttribute("pageCourante", page);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return "adminArtiste";
	}
	
	@GetMapping("/editOrCreateArtiste")
	public String editOrCreateArtiste(Model model, @RequestParam(name="artisteId", required = false, defaultValue = "-1") int id) throws Exception { 
		
		Optional<Artists> opArtiste; 
		Artists artiste;
		
		try {
			opArtiste = this.service.findArtisteById(id);
			this.types = this.service.findAllTypes();
			this.checkedObjectArray = this.service.findTypesChecked(id);
			if(opArtiste.isPresent())
				artiste = opArtiste.get();
			else
				artiste = new Artists();
			
			model.addAttribute("artistes", artiste);
			model.addAttribute("checkedObjectArray", this.checkedObjectArray);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return "adminArtisteForm";
	}
	
	@PostMapping("/saveOrUpdateArtiste")
	public String saveOrUpdateArtiste(Model model,@Valid @ModelAttribute("artistes") Artists artitste, BindingResult result,
			@RequestParam(name="artisteTypes", required = false) Integer[] artisteTypes) throws Exception{

		try {
			
			if(result.hasErrors() || artisteTypes==null) {
				model.addAttribute("checkedObjectArray", this.checkedObjectArray);
				model.addAttribute("typeErrors", artisteTypes);
				return "adminArtisteForm";
			}
			
			this.service.saveArtiste(artitste,artisteTypes);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/admin/artiste";
	}
	
	@GetMapping("/deleteArtiste")
	public String deleteArtiste(@RequestParam(name="artisteId") int id) throws Exception{
		
		try {
			this.service.deleteArtisteById(id);;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "redirect:/admin/artiste";
	}
}
