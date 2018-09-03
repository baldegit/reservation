package com.balde.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balde.beans.Response;
import com.balde.entity.Roles;
import com.balde.service.IAdminService;

@RestController
@RequestMapping("/roles")
public class RoleRestController {
	
	@Autowired
	private IAdminService service;
	
	@PostMapping
	public ResponseEntity<?> saveRole(@Valid @RequestBody Roles role, BindingResult result){
				
		Response response;
		
		if(result.hasErrors()) {
			response = new Response(null, HttpStatus.NOT_ACCEPTABLE, result.getFieldError().toString());
			return new ResponseEntity<>(response,response.getStatus());
		}
		
		Optional<Roles> optRole;
		try {
			optRole = this.service.saveRole(role);
			if(optRole.isPresent()) {
				response = new Response(role, HttpStatus.CREATED, "Role enregistrer avec succee");
				
			}else {
				response = new Response(role, HttpStatus.CONFLICT, "une erreur est survenu ");
			}
			return new ResponseEntity<>(response,response.getStatus());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new Response(role, HttpStatus.EXPECTATION_FAILED, e.getMessage());
			return new ResponseEntity<>(response,response.getStatus());
		}

	}
	
//	@GetMapping("/")
//	public ResponseEntity<?> getRoles(){
//		Response response = null;
//		try {
//			List<Roles> r = this.service.findAllRole();
//			response = new Response(r, HttpStatus.ACCEPTED,"@@@@@@@@@@@@@@@@@@@");
//			return new ResponseEntity<>(response,response.getStatus());
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return new ResponseEntity<>(response,response.getStatus());
//		}
////		return new ResponseEntity<>(response,response.getStatus());
//	}
}
