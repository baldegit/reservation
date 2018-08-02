package com.balde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.balde.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{
//	public Users findByEmail(@Param("e") String e);
}
