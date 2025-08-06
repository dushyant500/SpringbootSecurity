package com.Dushyant.SpringSecPractise.Services;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Dushyant.SpringSecPractise.Model.SystemUsers;
import com.Dushyant.SpringSecPractise.Repository.UsersRepository;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	UsersRepository repo;
	
	@Autowired 
	AuthenticationManager authManager;
	
	@Autowired
	JWTService jwtService;
	
	BCryptPasswordEncoder enc = new BCryptPasswordEncoder(12);

	public String JWTToken(SystemUsers users) {
		Authentication auth = authManager.
				authenticate(new UsernamePasswordAuthenticationToken(users.getName(), users.getPassword()));
		if(auth.isAuthenticated()) {
			return jwtService.getJWTToken(users);
		}
		
		return "Unauthorized";
	}
	
	public ResponseEntity<?> saveUser(SystemUsers u) {
		u.setPassword(enc.encode(u.getPassword()));//encryption
		if (repo.save(u)!=null) {
		return new ResponseEntity(HttpStatus.CREATED);
		}else {
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	public SystemUsers finduserbyUsername(String username) {
		return repo.findByName(username);
		
	}

	public ResponseEntity<List<SystemUsers>> getAll() {
		List<SystemUsers> l = repo.findAll();
		if(!l.isEmpty()) {
			return new ResponseEntity(l,HttpStatus.OK);
		}
		return new ResponseEntity("No users found",HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<SystemUsers> getUserById(int id) {
		SystemUsers user=repo.getById(id);
		if(user!=null) {
			return new ResponseEntity(user,HttpStatus.OK);
		}
		return new ResponseEntity("No user found",HttpStatus.NOT_FOUND);
	}

	@Transactional
	public ResponseEntity<?> update(SystemUsers user) {
		if(getUserById(user.getId())==null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
		user.setPassword(enc.encode(user.getPassword()));//encryption
		if (repo.save(user)!=null) {
		return new ResponseEntity(HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}
		
	}

	public ResponseEntity<?> delete(int id) {
		if(getUserById(id)==null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		repo.delete(getUserById(id).getBody());
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}

	
	
	
	

}
