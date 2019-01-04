package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long>{
	
	
	

}
