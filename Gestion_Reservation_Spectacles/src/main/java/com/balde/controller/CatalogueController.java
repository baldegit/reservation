package com.balde.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.balde.entity.Representations;
import com.balde.entity.Users;
import com.balde.service.IAdminService;

@Controller
public class CatalogueController {
	
	private static final int PAGES_SIZE = 5;
	
	private final String ERROR_CODE = "Les deux mots de passe doivent être identiques! ";
	
	@Autowired
	private IAdminService service;
	
	
	@GetMapping(value = {"/","home"})
	public String homePage(Model model,@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "motCle", required = false, defaultValue="") String motCle) throws Exception {

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
		
		return "userTemplates/catalogueHome";
	}
	
	@GetMapping("/catalogueDetail")
	public String detail(Model model,@RequestParam(name = "idRep", required = true) int id) throws Exception {
		Optional<Representations> representation;
		try {
			representation = this.service.findRepresentationById(id);
			model.addAttribute("representation", representation.get());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "userTemplates/catalogueDetail";
	}
	
	@GetMapping("/catalogueReservation")
	public String reservation(Model model,@RequestParam(name = "idRep", required = true) int id) throws Exception {
		Optional<Representations> representation;
		try {
			representation = this.service.findRepresentationById(id);
			model.addAttribute("representation", representation.get());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "userTemplates/catalogueReservation";
	}
	
	@GetMapping(value="/getPhoto",produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody()
	public byte[] getPhoto(int idShow) {
		
		try {
			File f = this.service.getPhotoForAShow(idShow);
			return IOUtils.toByteArray(new FileInputStream(f));
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/stripePayments")
	public String stripPayement(@RequestParam(name = "id") int id) throws Exception {
			
		try {
			this.service.updatePlace(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "redirect:/home";
	}
	
	@GetMapping("/editOrCreateNewUser")
	public String nouveauCompte(Model model, @RequestParam(name="userId", required = false, defaultValue = "-1") int id) throws Exception {
		
		Optional<Users> optUser;
		Users user;
		String route;
		try {
			optUser = this.service.findUserById(id);
			if(optUser.isPresent()) {
				user = optUser.get();
				route = "userProfil";
			}
			else {
				user = new Users();
				route = "userForm";
			}
			
			model.addAttribute("user", user);			
			return "userTemplates/"+route;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
			
	}
	
	@PostMapping("/saveOrUpdateUser")
	public String saveUser(Model model,@Valid @ModelAttribute("user") Users u, BindingResult result) {
		
		try {
			System.out.println("p "+u.getPassword()+" pc "+u.getConfirmPassword()+" != "+u.getPassword() != u.getConfirmPassword());
			if(!u.getPassword().equals(u.getConfirmPassword()))
				result.addError(new FieldError("user", "confirmPassword",this.ERROR_CODE));

			
			if(result.hasErrors())
				return "userTemplates/userForm";
			
			this.service.saveUser(u);
			return "redirect:/home";
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
		//userProfil
		
	}
}
