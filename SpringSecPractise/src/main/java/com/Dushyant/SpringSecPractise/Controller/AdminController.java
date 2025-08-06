package com.Dushyant.SpringSecPractise.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Dushyant.SpringSecPractise.Model.SystemUsers;
import com.Dushyant.SpringSecPractise.Services.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService userSer;
	
	/*
	 {
  "id":7,
  "name": "root1",
  "password": "r123",
  "role": "ROLE_ADMIN"
}
	  */
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody SystemUsers user){
		
		return userSer.saveUser(user);
	}
	
	@GetMapping("/allUsers")
	public ResponseEntity<List<SystemUsers>> getAllUsers(){
		return userSer.getAll();
	}
	
	@GetMapping("/userById/{id}")
	public ResponseEntity<SystemUsers> getUserById(@PathVariable int id)
	{
		return userSer.getUserById(id);
	}
	
	@PostMapping("/update-User")
	public ResponseEntity<?> updateUser(@RequestBody SystemUsers user){
		return userSer.update(user);
		
	}

	@PostMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@RequestParam boolean b, @PathVariable int id){
		if(!b) {
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}
		return userSer.delete(id);
	}
	
	
}
