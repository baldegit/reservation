package com.balde.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityControler {
	
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
	
//	@GetMapping("/error")
//	public String error() {
//		return "error";
//	}
}
