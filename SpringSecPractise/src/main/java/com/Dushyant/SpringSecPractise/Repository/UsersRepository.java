package com.Dushyant.SpringSecPractise.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Dushyant.SpringSecPractise.Model.SystemUsers;

@Repository
public interface UsersRepository extends JpaRepository<SystemUsers, Integer>{

	SystemUsers findByName(String name);
	
}
