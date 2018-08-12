package com.balde.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.balde.beans.CheckedObject;
import com.balde.entity.*;
import com.balde.service.IAdminService;

@Controller
@RequestMapping("/admin/show")
public class ShowController {
	
	private static final int PAGES_SIZE = 5;
	List<Locations> locations;
	private List<Artists> artistes;
	private List<CheckedObject<Artists>> checkedObjectArray;
	
	@Autowired
	private IAdminService service;
	
	@GetMapping(value = {"/","show"})
	public String goToTypePage(Model model,@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "motCle", required = false, defaultValue="") String title) throws Exception  {
		
		int [] numPage;
		Page<Shows> show;
		List<ArtistShow> artisteShow;
		
		try {
			List<Object> object = this.service.findAllShowByPage(page,title, this.PAGES_SIZE);
			
			show = (Page<Shows>) object.get(0);
			numPage = (int []) object.get(1);
			
			artisteShow = this.service.getAllArtistesShows();
			
			model.addAttribute("motCle", title);
			model.addAttribute("shows", show);
			model.addAttribute("numPage", numPage);
			model.addAttribute("pageCourante", page);
			model.addAttribute("artisteShow", artisteShow);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return "adminTemplates/adminShow";
	}
	
	@GetMapping("/editOrCreate")
	public String editOrCreateType(Model model, @RequestParam(name="showId", required = false, defaultValue = "-1") int id) throws Exception { 
		
		Optional<Shows> optShow; 
		Shows show;
		
		
		try {
			optShow = this.service.findShowById(id);
			this.locations = this.service.findAllLocation();
			this.checkedObjectArray = this.service.findArtisteChecked(id);
			
			if(optShow.isPresent())
				show = optShow.get();
			else
				show = new Shows();
			
			model.addAttribute("locations", locations);
			model.addAttribute("show", show);
			model.addAttribute("checkedObjectArray", this.checkedObjectArray);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return "adminTemplates/adminShowForm";
	}
	
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdateType(Model model,@Valid @ModelAttribute("show") Shows show, BindingResult result,
			@RequestParam(name="artistes", required = false) Integer[] artistes,
			@RequestParam(name="picture") MultipartFile file) throws Exception{
		
		try {
			if(result.hasErrors() || (file.isEmpty() && show.getId() <= 0 || artistes == null)) {
				model.addAttribute("locations", locations);
				model.addAttribute("file", file.isEmpty());
				model.addAttribute("checkedObjectArray", this.checkedObjectArray);
				return "adminTemplates/adminShowForm";
			}
			
			
			this.service.saveShow(show,file,artistes);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/admin/show/";
	}
	
	@GetMapping("/delete")
	public String deleteType(@RequestParam(name="showId") int id) throws Exception{
		
		try {
			this.service.deleteShowById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "redirect:/admin/show/";
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
	
}
