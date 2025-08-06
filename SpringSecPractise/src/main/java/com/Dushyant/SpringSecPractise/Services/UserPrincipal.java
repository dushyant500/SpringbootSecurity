package com.Dushyant.SpringSecPractise.Services;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Dushyant.SpringSecPractise.Model.SystemUsers;

public class UserPrincipal implements UserDetails {
	
	SystemUsers users;
	
	public UserPrincipal(SystemUsers users) {
		this.users = users;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return  Collections.singleton(new SimpleGrantedAuthority(users.getRole()));
	}

	@Override
	public String getPassword() {
		
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		
		return users.getName();
	}

}
