package com.Dushyant.SpringSecPractise.Configurations;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Dushyant.SpringSecPractise.Filters.JWTFilter;
import com.Dushyant.SpringSecPractise.Services.UserDetailsService;



@EnableWebSecurity
@Configuration
public class Configure {
	
	@Autowired
	private JWTFilter jwtFilter;
	
	@Autowired
	private UserDetailsService userdetailsSec;
	
	private BCryptPasswordEncoder enc = new BCryptPasswordEncoder(12);

	@Bean
	public SecurityFilterChain securityFilters(HttpSecurity httpsec) throws Exception {
		/*
		 .authorizeRequests()
        .requestMatchers("/admin/**").hasRole("ADMIN")
        .requestMatchers("/user/**").hasRole( "USER")
        .anyRequest().authenticated()
        .and()
        .formLogin()
       // Specify the URL for the registration page

        .and()
        .logout().logoutSuccessUrl("/").permitAll();*/
		
		return httpsec
		.csrf(Customizer ->Customizer.disable())
		.authorizeHttpRequests(Request->Request.requestMatchers("/security/login",
				"/security/home","/security/JWT","/exception/**").permitAll()
				.requestMatchers("/admin/**").hasRole("Admin")
				.anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults())
		.formLogin(Customizer.withDefaults())
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		.build();
	}
	
	@Bean
	public AuthenticationProvider authenticate() {
		DaoAuthenticationProvider daoauth = new DaoAuthenticationProvider();
		daoauth.setPasswordEncoder(enc);
		daoauth.setUserDetailsService(userdetailsSec);
		return daoauth;
	}
	
	@Bean 
	public AuthenticationManager authmanage(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
}
