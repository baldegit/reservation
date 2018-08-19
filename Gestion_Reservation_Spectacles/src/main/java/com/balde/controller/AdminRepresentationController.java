package com.balde.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
@RequestMapping("/admin/representation")
public class AdminRepresentationController {

	private static final int PAGES_SIZE = 5;
	
	private List<Shows> shows;
	
	@Autowired
	private IAdminService service;
	
	@GetMapping(value = {"/","representation"})
	public String goToTypePage(Model model,@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "motCle", required = false, defaultValue="") String motCle) throws Exception  {
		
		int [] numPage;
		Page<Representations> representations;
		
		try {
			List<Object> object = this.service.findAllRepresentationByPage(page,motCle, this.PAGES_SIZE);
			
			representations = (Page<Representations>) object.get(0);
			numPage = (int []) object.get(1);
			
			model.addAttribute("motCle", motCle);
			model.addAttribute("representations", representations);
			model.addAttribute("numPage", numPage);
			model.addAttribute("pageCourante", page);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return "adminTemplates/adminRepresentation";
	}
	
	@GetMapping("/editOrCreate")
	public String editOrCreateType(Model model, @RequestParam(name="representationId", required = false, defaultValue = "-1") int id) throws Exception { 
		
		Optional<Representations> optRep; 
		Representations representation;
		
		
		try {
			optRep = this.service.findRepresentationById(id);
			this.shows = this.service.findAllShow();
			
			if(optRep.isPresent())
				representation = optRep.get();
			else
				representation = new Representations();
			
			model.addAttribute("representation", representation);
			model.addAttribute("shows", this.shows);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return "adminTemplates/adminRepresentationForm";
	}
	
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdateType(Model model,@Valid @ModelAttribute("representation") Representations rep, BindingResult result) throws Exception{
		
		try {
			if(result.hasErrors()) {
				model.addAttribute("shows", this.shows);
				return "adminTemplates/adminRepresentationForm";
			}
			
			this.service.saveRepresentation(rep);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/admin/representation/";
	}
	
	@GetMapping("/delete")
	public String deleteType(@RequestParam(name="representationId") int id) throws Exception{
		
		try {
			this.service.deleteRepresentationById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "redirect:/admin/representation/";
	}
}
