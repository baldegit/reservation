package com.balde.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.balde.entity.*;

@Repository
public interface ArtistShowRepository extends JpaRepository<ArtistShow, Integer>{
	
	@Query("select a from ArtistShow a where a.show.id = :x")
	public List<ArtistShow> findArtitsteShowByShowId(@Param("x") int idShow);
	
	
	@Query("delete from ArtistShow a where a.show.id = :x")
	@Modifying
	public void deleteArtitsteShowByShowId(@Param("x") int idArtiste);
}
