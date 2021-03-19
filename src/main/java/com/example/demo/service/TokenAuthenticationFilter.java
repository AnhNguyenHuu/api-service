package com.example.demo.service;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	UserService userService;
	
	public TokenAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserService userService) {
		super();
		this.jwtTokenProvider = jwtTokenProvider;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
		String username;
		String authToken = jwtTokenProvider.getToken(request);
		if(authToken !=null) {
			username = jwtTokenProvider.getUsernameFromToken(authToken);
			System.out.println("username: " + username);
			if(username !=null) {
				UserDetails userDetails = userService.loadUserByUsername(username);
				if(userDetails !=null) {
					TokenBasedAuthentication authentication = new TokenBasedAuthentication(authToken, userDetails);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}
		 } catch (Exception ex) {
	          ex.printStackTrace();
	        }
		filterChain.doFilter(request, response);
	}
	
}
