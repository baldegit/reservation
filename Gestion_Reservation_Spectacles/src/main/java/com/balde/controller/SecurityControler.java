package com.balde.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balde.entity.Users;
import com.balde.service.IAdminService;

@Controller
public class SecurityControler {
	
	@Autowired
	private IAdminService service;
	
	@GetMapping(value = {"connexion","login"})
	public String connexion() {
		return "userTemplates/login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/login?logout";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "userTemplates/accessDenied";
	}
	
//	@GetMapping("/getAuthentifiedUser")
//	public @ResponseBody  Users getAuthentifiedUser() {
//		Users users = null;
//		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String email = auth.getName();
//		System.out.println(" @@ "+email+" Autho "+auth.getAuthorities()+" isAut "+auth.isAuthenticated() );
//		Optional<Users> optUser;
//		
//		try {
//			optUser = this.service.findUserByEmail(email);
//			if(optUser.isPresent())
//				users = optUser.get();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
	
//	@GetMapping("/error")
//	public String error() {
//		
//		return "error01";
//	}
}
