package com.balde.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.balde.entity.Artists;

@Repository
public interface ArtistsRepository extends JpaRepository<Artists, Integer> {
	
	@Query("select a from Artists a where a.firstName like %:x%")
	public Page<Artists> findArtisteByMotCle(@Param("x") String mc, Pageable pageable);
}
