package com.balde.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.balde.entity.*;

@Repository
public interface RepresentationsRepository extends JpaRepository<Representations, Integer>{
	@Query("select r from Representations r where r.show.title like %:x%")
	public Page<Representations> findRepresentationByShowTitle(@Param("x") String mc, Pageable pageable);
}
