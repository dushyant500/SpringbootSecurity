package com.Dushyant.SpringSecPractise.Services;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.Dushyant.SpringSecPractise.Model.SystemUsers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {


	

	
	public String getJWTToken(SystemUsers users) {
		Map<String, Object> claimsMap = new HashMap<String, Object>();
		return Jwts.builder().addClaims(claimsMap).subject(users.getName())
				.issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis() + 60 * 60 * 100))
				.signWith(getKey()).compact();
				
				
		
	}

	private static final String SECRET = "your-256-bit-dushya-mine-256-bit-secret"; // must be at least 256 bits for HS256

	private SecretKey getKey() {
	    byte[] keyBytes = Decoders.BASE64.decode(Base64.getEncoder().encodeToString(SECRET.getBytes()));
	    return Keys.hmacShaKeyFor(keyBytes);
	}


	public String extractUsername(String token) {
		return extractClaims(token, Claims::getSubject);
	}


	private<T> T extractClaims(String token, Function<Claims, T> resolver) {
		final Claims claims = extractAllClaims(token);
		return resolver.apply(claims);
	}


	private Claims extractAllClaims(String token) {
		
		return Jwts.parser().verifyWith(getKey()).
				build().parseSignedClaims(token).getPayload();
	}

	  public boolean validateToken(String token, UserDetails userDetails) {
	        final String userName = extractUsername(token);
	        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

	    private boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    private Date extractExpiration(String token) {
	        return (Date) extractClaims(token, Claims::getExpiration);
	    }

	
}
