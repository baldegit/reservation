package com.balde.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.balde.entity.Types;

@Repository
public interface TypesRepository extends JpaRepository<Types, Integer>{
	
	@Query("select t from Types t where t.type like %:x%")
	public Page<Types> findTypeByMotCle(@Param("x") String mc, Pageable pageable);
}
