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
@RequestMapping("/admin/localite")
public class AdminLocaliteController {
	
	private static final int PAGES_SIZE = 5;
	
	@Autowired
	private IAdminService service;
	
	@GetMapping(value = {"/","localite"})
	public String goToTypePage(Model model,@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "motCle", required = false, defaultValue="") String codePostal) throws Exception  {
		
		int [] numPage;
		Page<Localities> localites;
		
		try {
			List<Object> object = this.service.findAllLocaliteByPage(page,codePostal, this.PAGES_SIZE);
			
			localites = (Page<Localities>) object.get(0);
			numPage = (int []) object.get(1);
			
			model.addAttribute("motCle", codePostal);
			model.addAttribute("localites", localites);
			model.addAttribute("numPage", numPage);
			model.addAttribute("pageCourante", page);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return "adminTemplates/adminLocalite";
	}
	
	@GetMapping("/editOrCreate")
	public String editOrCreateType(Model model, @RequestParam(name="localiteId", required = false, defaultValue = "-1") int id) throws Exception { 
		
		Optional<Localities> optLocalite; 
		Localities localite;
		
		try {
			optLocalite = this.service.findLocaliteById(id);
			if(optLocalite.isPresent())
				localite = optLocalite.get();
			else
				localite = new Localities();
			
			model.addAttribute("localite", localite);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return "adminTemplates/adminLocaliteForm";
	}
	
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdateType(Model model,@Valid @ModelAttribute("localite")Localities localite, BindingResult result) throws Exception{
		
		try {
			if(result.hasErrors())
				return "adminTemplates/adminLocaliteForm";
			
			this.service.saveLocalite(localite);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/admin/localite/";
	}
	
	@GetMapping("/delete")
	public String deleteType(@RequestParam(name="localiteId") int id) throws Exception{
		
		try {
			this.service.deleteLocaliteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "redirect:/admin/localite/";
	}
}
