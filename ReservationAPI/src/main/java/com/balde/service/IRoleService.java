package com.balde.service;

import java.util.List;
import java.util.Optional;

import com.balde.entity.Roles;

public interface IRoleService {
	
	public Optional<List<Roles>> getAllRole() throws Exception ; 
	public Optional<Roles> saveNewRole(Roles r) throws Exception ;
}
