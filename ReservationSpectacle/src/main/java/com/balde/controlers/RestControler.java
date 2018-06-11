package com.balde.controlers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balde.entity.*;
import com.balde.service.api.RoleServiceImpl;
import com.balde.service.api.RolesFromAPI;

@RestController
@RequestMapping("/roles")
public class RestControler {
	//url -> http://localhost:8080/ReservationSpectacle/roles/
	@Autowired
	RoleServiceImpl role;
	
	@GetMapping
//	public ResponseEntity<List<Roles>> getAllRoles(){
	public ResponseEntity<RolesFromAPI> getAllRoles(){
		
		try {
//			Optional<List<Roles>> o = this.role.getAllRole();
			Optional<RolesFromAPI> o = this.role.getAllRole();
			if(o.isPresent()) {
				return new ResponseEntity<>(o.get(),HttpStatus.OK);
			}else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> addRole(@RequestBody Roles r){
		try {
			Optional<Roles> o  = this.role.saveNewRole(r);
			if(o.isPresent()) {
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>("le role n'a pas ete crée ",HttpStatus.CONFLICT);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Exception ",HttpStatus.EXPECTATION_FAILED);
		}
	}
}
