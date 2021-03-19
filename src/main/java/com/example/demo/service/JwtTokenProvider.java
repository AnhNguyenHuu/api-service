package com.example.demo.service;

import java.security.Key;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Component
public class JwtTokenProvider {
	
	@Value("${jwt.secret:mysecret}")
    public String secret;
	
	private final long JWT_EXPIRATION = 604800;
	
	public String generateToken(String username) {
		Date now = new Date();
		Date exprity = new Date(now.getTime() + JWT_EXPIRATION * 1000);
		return Jwts.builder()
				   .setSubject(username)
				   .setIssuedAt(now)
				   .setExpiration(exprity)
				   .signWith(SignatureAlgorithm.HS512, secret)
				   .compact();
	}
//	public String getUserIdFromJWT(String token) {
//        Claims claims = Jwts.parser()
//                            .setSigningKey(JWT_SECRET)
//                            .parseClaimsJws(token)
//                            .getBody();
//
//        return claims.getSubject();
//    }
	 public boolean validateToken(String authToken) {
	        try {
	            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
	            return true;
	        } catch (MalformedJwtException ex) {
	            System.out.println("Invalid JWT token");
	        } catch (ExpiredJwtException ex) {
	        	System.out.println("Expired JWT token");
	        } catch (UnsupportedJwtException ex) {
	        	System.out.println("Unsupported JWT token");
	        } catch (IllegalArgumentException ex) {
	        	System.out.println("JWT claims string is empty.");
	        }
	        return false;
	    }
	 
	 public String getUsernameFromToken(String token) {
		 String username = null;
		 try {
			 final Claims claims = this.getAllClaimsFromToken(token);
			 if(claims !=null) {
				 username = claims.getSubject();
			 }
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return username;
	 }
	 
	 private Claims getAllClaimsFromToken(String token) {
	        Claims claims;
	        try {
	            claims = Jwts.parser()
	                    .setSigningKey(secret)
	                    .parseClaimsJws(token)
	                    .getBody();
	        } catch (Exception e) {
	        	e.printStackTrace();
	            claims = null;
	        }
	        return claims;
	    }
	 
	 public String getToken(HttpServletRequest request) {
		 String bearHeader = request.getHeader("Authorization");
		 System.out.println("bearHeader: " + bearHeader);
		 if(StringUtils.hasText(bearHeader) && bearHeader.startsWith("Bearer ")) {
			 return bearHeader.substring(7);
		 }
		 return null;
	 }
}