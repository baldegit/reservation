package com.balde.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.balde.entity.*;
import com.balde.repository.*;

@Service
@Transactional()
public class AdminServiceImpl implements IAdminService{
	
	@Autowired
	private TypesRepository typeRepo;

	public AdminServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Gestion du type d'artiste ----------------------------------------------
	 * */
	
	@Override
	public List<Object> findAllTypesByPage(int page,String motCle,int size) throws Exception {
		// TODO Auto-generated method stub
		List<Object> object = new ArrayList<>();
		int [] numPage;
		try {
			
			Page<Types> types = this.typeRepo.findTypeByMotCle(motCle,PageRequest.of(page, size,Direction. ASC , "type"));
			
			numPage = new  int[types.getTotalPages()];
			for(int i = 0; i < types.getTotalPages(); i++) 
				numPage[i] = i;
			
			object.add(types);
			object.add(numPage);
			
			return object;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Optional<Types> findTypeById(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			Optional<Types> t = this.typeRepo.findById(id);
			return t;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Optional<Types> saveType(Types t) throws Exception {
		// TODO Auto-generated method stub
		try {
			Types type = this.typeRepo.save(t);
			return Optional.ofNullable(type);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void deleteTypeById(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.typeRepo.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
	
	
}
