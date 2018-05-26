package com.balde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balde.entity.Localities;

@Repository
public interface LocalitiesRepository extends JpaRepository<Localities, Integer>{

}
