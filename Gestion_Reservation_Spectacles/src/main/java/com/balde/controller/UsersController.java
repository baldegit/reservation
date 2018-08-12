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
@RequestMapping("/admin/user")
public class UsersController {
	
	private static final int PAGES_SIZE = 5;
	
	@Autowired
	private IAdminService service;
	
	@GetMapping(value = {"/","user"})
	public String goToTypePage(Model model,@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "motCle", required = false, defaultValue="") String login) throws Exception  {
		
		int [] numPage;
		Page<Users> users;
		
		try {
			List<Object> object = this.service.findAllUsersByPage(page,login, this.PAGES_SIZE);
			
			users = (Page<Users>) object.get(0);
			numPage = (int []) object.get(1);
			
			model.addAttribute("motCle", login);
			model.addAttribute("users", users);
			model.addAttribute("numPage", numPage);
			model.addAttribute("pageCourante", page);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return "adminTemplates/adminUser";
	}
	
	@GetMapping("/editOrCreate")
	public String editOrCreateType(Model model, @RequestParam(name="userId", required = false, defaultValue = "-1") int id) throws Exception { 
		
		Optional<Users> optUser; 
		Users user;
		
		try {
			optUser = this.service.findUserById(id);
			if(optUser.isPresent())
				user = optUser.get();
			else
				user = new Users();
			
			model.addAttribute("user", user);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "adminTemplates/adminUserForm";
	}
	
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdateType(Model model,@Valid @ModelAttribute("user")Users user, BindingResult result) throws Exception{
		
		try {
			if(result.hasErrors())
				return "adminTemplates/adminUserForm";
			
			this.service.saveUser(user);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/admin/user/";
	}
	
	@GetMapping("/delete")
	public String deleteType(@RequestParam(name="userId") int id) throws Exception{
		
		try {
			this.service.deleteUserById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "redirect:/admin/user/";
	}
}
