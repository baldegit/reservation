package com.balde.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.balde.entity.*;

@Repository
public interface ArtistsTypeRepository extends JpaRepository<ArtistType, Integer>{
	
	@Query("select at from ArtistType at where at.artist.id = :x")
	public List<ArtistType> findArtitsteTypeByArtisteId(@Param("x") int idArtiste);
	
	
	@Query("delete from ArtistType at where at.artist.id = :x")
	@Modifying
	public void deleteArtitsteTypeByArtisteId(@Param("x") int idArtiste);
}
