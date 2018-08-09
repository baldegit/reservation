package com.balde.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.balde.entity.*;

@Repository
public interface LocalitiesRepository extends JpaRepository<Localities, Integer>{
	@Query("select l from Localities l where l.postalCode like %:x%")
	public Page<Localities> findLocaliteByCodePostal(@Param("x") String mc, Pageable pageable);
}
