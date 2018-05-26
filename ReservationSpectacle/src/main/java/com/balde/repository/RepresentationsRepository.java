package com.balde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balde.entity.Representations;

@Repository
public interface RepresentationsRepository extends JpaRepository<Representations, Integer>{

}
