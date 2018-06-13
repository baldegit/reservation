package com.balde.controlers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.x509;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.balde.entity.*;
import com.balde.service.ICatalogueService;
import com.balde.service.api.RolesFromAPI;


@Controller
@RequestMapping("/admin")
public class adminControler {
	
	private static final String PAGE_ARTISTE_JS = "adminArtiste";
	private static final String PAGE_SHOW_JS = "adminShow";
	private static final String PAGE_ROLE_JS = "adminRole";
	private static final String PAGE_REPRESENTATION_JS = "representation";
	private static final String PAGE_SPECTACLE_JS = "spectacle";
	private static final String PAGE_UPADATE_SAVE_JS = "updateSaveArtiste";
	
	private static final String OBJECT_ARTISTE = "artistes";
	private static final String OBJECT_SHOW = "shows";
	private static final String OBJECT_ROLES = "roles";
	private static final String OBJECT_ARTISTE_TYPE = "artistesTypes";
	private static final String OBJECT_TOTAL_PAGE = "pages";
	
	private static final String MESSAGE = "message";
	//4242 4242 4242 4242
	@Autowired
	ICatalogueService catalogue;
	
	@GetMapping("/")
	public String adminHome() {
		return "adminHome";//artisteForAdmin
	}
	
	@GetMapping("/updateArtiste")
	public ModelAndView updateArtiste(@RequestParam(name = "id") int id) {
		ModelAndView mv = new ModelAndView();
	
			Optional<Artists> o = this.catalogue.getArtisteById(id);
			if(o.isPresent()) {
				mv.addObject(this.OBJECT_ARTISTE,o.get());
				mv.setViewName(this.PAGE_UPADATE_SAVE_JS);
			}else {
				mv.addObject(this.OBJECT_ARTISTE,null);
				mv.addObject(this.MESSAGE,"aucin artiste de correspond a l'id "+id);
				mv.setViewName("redirect:/admin/artiste");
			}

		return mv;
	}
	
	@GetMapping("/addArtiste")
	public ModelAndView addArtiste() {
		ModelAndView mv = new ModelAndView();
			
		Artists a = new Artists();
		
		mv.addObject(this.OBJECT_ARTISTE,a);
		mv.setViewName(this.PAGE_UPADATE_SAVE_JS);
		
		return mv;
	}
	
	@GetMapping("/addShow")
	public ModelAndView addShows() {
		ModelAndView mv = new ModelAndView();
			
		try {
			this.catalogue.updateShowFromApi(5);	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		mv.setViewName("redirect:/admin/shows");
		
		return mv;
	}
	

	
	@PostMapping("/saveOrUpdateArtiste")
	public ModelAndView saveOrUpdateArtiste(@ModelAttribute("artiste") Artists a) {
		ModelAndView mv = new ModelAndView();
		
		try {
			Optional o = this.catalogue.saveArtiste(a);
			if(o.isPresent()) {
				mv.addObject(this.MESSAGE," yes well done ");
				mv.setViewName("redirect:/admin/artiste");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mv.addObject(this.OBJECT_ARTISTE,a);
			mv.setViewName(this.PAGE_UPADATE_SAVE_JS);
			e.printStackTrace();
		}
		
		return mv;
	}
	
	@GetMapping("/deleteArtiste")
	public ModelAndView deleteArtiste(@RequestParam(name = "id") int id, RedirectAttributes atr) {
		ModelAndView mv = new ModelAndView();
		
		if(this.catalogue.deleteArtisteById(id).isPresent())
			atr.addAttribute("message", "l'artiste à ete supprimer avec succé");
		else
			atr.addFlashAttribute("message", "Une erreur est survenu lors de la supression");
	
		
		mv.setViewName("redirect:/admin/artiste");
		return mv;
	}
	
	@GetMapping("/deleteShow")
	public ModelAndView deleteShow(@RequestParam(name = "id") int id, RedirectAttributes atr) {
		ModelAndView mv = new ModelAndView();
		
		try {
			if(this.catalogue.deleteShows(id).isPresent())
				atr.addAttribute(this.MESSAGE, "le show a ete supprimer avec succé");
			else
				atr.addFlashAttribute(this.MESSAGE, "Une erreur est survenu lors de la supression");
		} catch (Exception e) {
			// TODO: handle exception
			atr.addFlashAttribute(this.MESSAGE, "Une Exception est survenu lors de la supression");
		}
		
		
		mv.setViewName("redirect:/admin/shows");
		return mv;
	}
	
	@GetMapping("/artiste")
	public ModelAndView artiste(@RequestParam(name = "page" ,defaultValue = "0", required = false) int page, 
			@RequestParam(name = "size", defaultValue = "3", required = false) int size) {
		
		ModelAndView mv = new ModelAndView();
		
		Optional<Page<Artists>> o = this.catalogue.getArtistesByPage(PageRequest.of(page, size, Direction.ASC,"firstName"));
		Optional<List<ArtistType>> o1 = this.catalogue.getArtistesTypes();
		
	
		
		if(o.get().getContent().size() > 0 ) {
			mv.addObject(this.OBJECT_ARTISTE, o.get().getContent());
			mv.addObject(this.OBJECT_TOTAL_PAGE, o.get().getTotalPages());
			mv.addObject(OBJECT_ARTISTE_TYPE, o1.get());
		}else {
			mv.addObject(MESSAGE, "la liste des artites est vide ");
			
			mv.addObject(this.OBJECT_ARTISTE, null);
			mv.addObject(this.OBJECT_TOTAL_PAGE, null);
			mv.addObject(OBJECT_ARTISTE_TYPE, null);
		}
		mv.setViewName(this.PAGE_ARTISTE_JS);
		
		
		
		return mv;
	}
	
	@GetMapping("/shows")
	public ModelAndView shows(@RequestParam(name = "page" ,defaultValue = "0", required = false) int page, 
			@RequestParam(name = "size", defaultValue = "3", required = false) int size) {
		
		ModelAndView mv = new ModelAndView();
		
		try {
			Optional<Page<Shows>> o = this.catalogue.getAllShows(PageRequest.of(page, size));
			if(o.get().getContent().size() > 0) {
				mv.addObject(this.OBJECT_SHOW, o.get().getContent());
				mv.addObject(this.OBJECT_TOTAL_PAGE, o.get().getTotalPages());
			}else {
				mv.addObject(MESSAGE, "la liste des shows est vide ");
				mv.addObject(this.OBJECT_SHOW, null);
				mv.addObject(this.OBJECT_TOTAL_PAGE, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			mv.addObject(MESSAGE, "Erreur survenu cote serveur ");
		}
	
		mv.setViewName(this.PAGE_SHOW_JS);
	
		
		return mv;
	}
	
	@GetMapping("/roles")
	public ModelAndView roles() {
		ModelAndView mv = new ModelAndView();
		try {
			Optional<RolesFromAPI> o = this.catalogue.getRolesFromAPI(); 
			
			if(o.isPresent()) {
				mv.addObject(this.OBJECT_ROLES, o.get().getRoles());
			}else {
				mv.addObject(this.OBJECT_ROLES, null);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			mv.addObject(MESSAGE, "Erreur survenu cote serveur ");
		}
		mv.setViewName(this.PAGE_ROLE_JS);
		
		System.out.println("#################################");
		return mv;
	}
	
	
	// artites shows representations spectacles
	// dans un controloleur normal
	@GetMapping("/error")
	public String error() {
		return "";
	}
}
