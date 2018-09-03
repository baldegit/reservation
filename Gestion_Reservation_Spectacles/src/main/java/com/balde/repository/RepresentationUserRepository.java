package com.balde.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.balde.entity.RepresentationUser;

@Repository
public interface RepresentationUserRepository extends JpaRepository<RepresentationUser, Integer>{
	@Query("select r from RepresentationUser r where r.user.id = :x")
	public List<RepresentationUser> findRepresentationsUserByUserId(@Param("x") int userId);
	
	@Query("select r from RepresentationUser r where r.user.id = :x and r.representation.id = :y")
	public RepresentationUser findRepUserByUserRepId(@Param("x") int userId,@Param("y") int repId);
}
