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
@RequestMapping("/admin/role")
public class AdminRoleController {
	
	private static final int PAGES_SIZE = 5;
	
	@Autowired
	private IAdminService service;
	
	@GetMapping(value = {"/","role"})
	public String goToTypePage(Model model,@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "motCle", required = false, defaultValue="") String role) throws Exception  {
		
		int [] numPage;
		Page<Roles> roles;
		
		try {
			List<Object> object = this.service.findAllRolesByPage(page,role, this.PAGES_SIZE);
			
			roles = (Page<Roles>) object.get(0);
			numPage = (int []) object.get(1);
			
			model.addAttribute("motCle", role);
			model.addAttribute("roles", roles);
			model.addAttribute("numPage", numPage);
			model.addAttribute("pageCourante", page);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return "adminTemplates/adminRole";
	}
	
	@GetMapping("/editOrCreate")
	public String editOrCreateType(Model model, @RequestParam(name="roleId", required = false, defaultValue = "-1") int id) throws Exception { 
		
		Optional<Roles> optRoles; 
		Roles roles;
		
		try {
			optRoles = this.service.findRoleById(id);
			if(optRoles.isPresent())
				roles = optRoles.get();
			else
				roles = new Roles();
			
			model.addAttribute("roles", roles);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return "adminTemplates/adminRoleForm";
	}
	
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdateType(Model model,@Valid @ModelAttribute("roles")Roles role, BindingResult result) throws Exception{
		
		try {
			if(result.hasErrors())
				return "adminTemplates/adminRoleForm";
			
			this.service.saveRole(role);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/admin/role/";
	}
	
	@GetMapping("/delete")
	public String deleteType(@RequestParam(name="roleId") int id) throws Exception{
		
		try {
			this.service.deleteRoleById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "redirect:/admin/role/";
	}
}
