package com.balde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balde.entity.Artists;

@Repository
public interface ArtistsRepository extends JpaRepository<Artists, Integer> {

}
