package com.balde.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.balde.entity.Shows;

@Repository
public interface ShowsRepository extends JpaRepository<Shows, Integer>{
	
	@Query("select s from Shows s where s.title like %:x%")
	public Page<Shows> findShowByMotCle(@Param("x") String mc, Pageable pageable);
}
