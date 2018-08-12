package com.balde.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.balde.entity.Types;
import com.balde.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{
	
	@Query("select u from Users u where u.login like %:x%")
	public Page<Users> findUserByMotCle(@Param("x") String mc, Pageable pageable);
}
