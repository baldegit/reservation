package com.balde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balde.entity.Shows;

@Repository
public interface ShowsRepository extends JpaRepository<Shows, Integer>{

}
