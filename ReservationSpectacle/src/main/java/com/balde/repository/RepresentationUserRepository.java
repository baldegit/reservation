package com.balde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balde.entity.RepresentationUser;

@Repository
public interface RepresentationUserRepository extends JpaRepository<RepresentationUser, Integer>{

}
