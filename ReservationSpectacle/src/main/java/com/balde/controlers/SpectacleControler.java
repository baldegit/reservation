package com.balde.controlers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.balde.entity.Shows;
import com.balde.service.ICatalogueService;

@Controller
public class SpectacleControler {
	
	private static final String PAGE_HOME_JS = "userHome";
	private static final String PAGE_DETAIL_JS = "detailShow";
	private static final String PAGE_RESERVATION = "reservationShow";
	
	private static final String OBJECT_SHOW = "shows";
	
	
	private static final String OBJECT_TOTAL_PAGE = "pages";
	
	private static final String MESSAGE = "message";
	
	@Autowired
	ICatalogueService catalogue;
	
	@GetMapping("/")
	public ModelAndView getShow(@RequestParam(name = "page" ,defaultValue = "0", required = false) int page, 
			@RequestParam(name = "size", defaultValue = "6", required = false) int size) {
		
		
		ModelAndView mv = new ModelAndView();
		
		try {
			Optional<Page<Shows>> o = this.catalogue.getAllShows(PageRequest.of(page, size));
			if(o.get().getContent().size() > 0) {
				mv.addObject(this.OBJECT_SHOW, o.get().getContent());
				mv.addObject(this.OBJECT_TOTAL_PAGE, o.get().getTotalPages());
			}else {
				mv.addObject(this.MESSAGE, "la liste des shows est vide ");
				mv.addObject(this.OBJECT_SHOW, null);
				mv.addObject(this.OBJECT_TOTAL_PAGE, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			mv.addObject(MESSAGE, "Erreur survenu cote serveur ");
		}
	
		mv.setViewName(this.PAGE_HOME_JS);
		
		return mv;
	}
	
	@GetMapping("/detailShow")
	public ModelAndView detailShow(@RequestParam(name = "id") int id) {
		ModelAndView mv = new ModelAndView();
		
		try {
			Optional<Shows> o = this.catalogue.getShowsById(id);
			if(o.isPresent()) {
				mv.addObject(this.OBJECT_SHOW, o.get());
				mv.setViewName(this.PAGE_DETAIL_JS);
			}else {
				mv.addObject(this.MESSAGE, "le show na pa ete trouvé ");
				mv.setViewName("redirect:/");
			}
		} catch (Exception e) {
			// TODO: handle exception
			mv.addObject(this.MESSAGE, e.getMessage());
			mv.setViewName("redirect:/");
		}
		
		return mv;
	}
	
	@GetMapping("/reservationShow")
	public ModelAndView reverserPalce(@RequestParam(name = "id") int id) {
		ModelAndView mv = new ModelAndView();
		
		try {
			Optional<Shows> o = this.catalogue.getShowsById(id);
			if(o.isPresent()) {
				
				/* operation de verification de place disponible*/
				if(this.catalogue.isPlace(o.get())) {
					mv.addObject(this.OBJECT_SHOW, o.get());
					mv.setViewName(this.PAGE_RESERVATION);
				}else {
					mv.addObject(this.MESSAGE, "nombre de place insufisant");
					mv.setViewName("redirect:/");
				}
				
			}else {
				mv.addObject(this.MESSAGE, "le show na pa ete trouvé ");
				mv.setViewName("redirect:/");
			}
		} catch (Exception e) {
			// TODO: handle exception
			mv.addObject(this.MESSAGE, e.getMessage());
			mv.setViewName("redirect:/");
		}
		
		return mv;
	}
	
	@PostMapping("/stripePayments")
	public ModelAndView stripPayement(@RequestParam(name = "id") int id) {
		ModelAndView mv = new ModelAndView();
		this.catalogue.updatePlace(id);
		mv.setViewName("redirect:/");
		return mv;
	}
}
