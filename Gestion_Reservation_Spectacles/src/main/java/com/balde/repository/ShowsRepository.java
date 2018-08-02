package com.balde.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.balde.entity.Shows;

@Repository
public interface ShowsRepository extends JpaRepository<Shows, Integer>{
//	Page<Shows> findByTitle( String name,Pageable pageable);
}
