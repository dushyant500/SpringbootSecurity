package com.Dushyant.SpringSecPractise.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.Dushyant.SpringSecPractise.Model.SystemUsers;
import com.Dushyant.SpringSecPractise.Services.JWTService;
import com.Dushyant.SpringSecPractise.Services.UserService;



@RestController
@RequestMapping("/security")
public class SecController {

	@Autowired UserService userService;
	
	
	private BCryptPasswordEncoder enc = new BCryptPasswordEncoder(12);
	
	 @GetMapping("/home")
	    public String getHome() {
	        System.out.println("✅ /home endpoint reached!");
	        return "Welcome to Spring Security!";
	    }
	
	@PostMapping("/chek-user/crediantials")
	public ResponseEntity<?> checkUser(@RequestBody SystemUsers u){
		 SystemUsers users = userService.finduserbyUsername(u.getName());
		 if(users.getName().equals(u.getName()) &&
				 enc.matches(u.getPassword(), users.getPassword())	 ) {
			return new ResponseEntity("Authenticated",HttpStatus.OK);
		 }
		 return new ResponseEntity("Unauthenticated",HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/JWT",method=RequestMethod.POST)
	public String getJWT(@RequestBody SystemUsers users) {
		return userService.JWTToken(users);
	}
	
	
	
	
}
