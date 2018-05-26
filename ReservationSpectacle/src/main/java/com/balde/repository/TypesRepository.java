package com.balde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balde.entity.Types;

@Repository
public interface TypesRepository extends JpaRepository<Types, Integer>{

}
