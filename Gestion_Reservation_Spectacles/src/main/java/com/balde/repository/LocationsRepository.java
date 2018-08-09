package com.balde.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.balde.entity.*;

@Repository
public interface LocationsRepository extends JpaRepository<Locations, Integer>{
	@Query("select l from Locations l where l.address like %:x%")
	public Page<Locations> findLocationByAdresse(@Param("x") String mc, Pageable pageable);
}
