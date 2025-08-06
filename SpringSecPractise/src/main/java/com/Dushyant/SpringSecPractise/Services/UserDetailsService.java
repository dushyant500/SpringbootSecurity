package com.Dushyant.SpringSecPractise.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.Dushyant.SpringSecPractise.Model.SystemUsers;
import com.Dushyant.SpringSecPractise.Repository.UsersRepository;

@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

	@Autowired
	UsersRepository userRep;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
	SystemUsers users = userRep.findByName(name);
	if(users==null) {
		System.out.println("No Such User");
	    throw new UsernameNotFoundException("No user exist");
	}
		
		return new UserPrincipal(users);
	}

}
