package com.balde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balde.entity.ArtistShow;

@Repository
public interface ArtistShowRepository extends JpaRepository<ArtistShow, Integer>{

}
