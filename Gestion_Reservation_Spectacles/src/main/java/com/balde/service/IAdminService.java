package com.balde.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.balde.entity.Types;

public interface IAdminService {
	
	// Types
	public List<Object> findAllTypesByPage(int page,String motCle,int size) throws Exception;
	public Optional<Types> findTypeById(int id) throws Exception;
	public Optional<Types> saveType(Types t) throws Exception;
	public void deleteTypeById(int id) throws Exception;
	
	
}
