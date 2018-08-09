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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.balde.entity.Types;
import com.balde.service.IAdminService;

@Controller
@RequestMapping("/admin/type")
public class TypeController {
	
	private static final int PAGES_SIZE = 5;
	
	@Autowired
	private IAdminService service;
	
	// Types D'artiste -----------------------------------------------------------------------------------
	
		@GetMapping(value = {"/","type"})
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
			
			return "adminTemplates/adminType";
		}
		
		@GetMapping("/editOrCreate")
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
			
		
			return "adminTemplates/adminTypeForm";
		}
		
		@PostMapping("/saveOrUpdate")
		public String saveOrUpdateType(Model model,@Valid Types type, BindingResult result) throws Exception{
			
			try {
				if(result.hasErrors())
					return "adminTemplates/adminTypeForm";
				
				this.service.saveType(type);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "redirect:/admin/type/";
		}
		
		@GetMapping("/delete")
		public String deleteType(@RequestParam(name="typeId") int id) throws Exception{
			
			try {
				this.service.deleteTypeById(id);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return "redirect:/admin/type/";
		}
}
