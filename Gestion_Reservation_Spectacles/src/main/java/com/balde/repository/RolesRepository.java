package com.balde.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.balde.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer>{
	
	@Query("select r from Roles r where r.role like %:x%")
	public Page<Roles> findRolesByRole(@Param("x") String mc, Pageable pageable);
	
	public Roles findRolesByRole(String role);
}
