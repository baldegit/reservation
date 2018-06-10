package com.balde.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balde.entity.Roles;
import com.balde.repository.RoleRepository;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	RoleRepository roleRepo;
	
	@Override
	public Optional<List<Roles>> getAllRole() throws Exception {
		// TODO Auto-generated method stub
		
		try {
			this.roleRepo.findAll();
			return Optional.ofNullable(this.roleRepo.findAll());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public Optional<Roles> saveNewRole(Roles r) throws Exception  {
		// TODO Auto-generated method stub
		try {
			return Optional.ofNullable(this.roleRepo.save(r));
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

}
