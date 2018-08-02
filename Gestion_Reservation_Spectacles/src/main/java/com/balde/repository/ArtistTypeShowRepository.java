package com.balde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balde.entity.ArtistTypeShow;

@Repository
public interface ArtistTypeShowRepository extends JpaRepository<ArtistTypeShow, Integer>{

}
