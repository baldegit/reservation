package com.balde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balde.entity.Locations;

@Repository
public interface LocationsRepository extends JpaRepository<Locations, Integer>{

}
