package com.balde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balde.entity.ArtistType;

@Repository
public interface ArtistsTypeRepository extends JpaRepository<ArtistType, Integer>{

}
