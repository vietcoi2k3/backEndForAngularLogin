package com.example.SecurityAndToken.config;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.SecurityAndToken.service.JwtService;
import com.example.SecurityAndToken.service.UserDetailServiceToken;


public class JwtConfig extends OncePerRequestFilter{

	@Autowired
	private UserDetailServiceToken userDetailServiceToken;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		 String authHeader = request.getHeader("Authorization");
	     String token = null;
	     String username = null;
	     if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            token = authHeader.substring(7);
	            username = jwtService.extractUsername(token);
	            System.out.println(username);
	        }
	     if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	    	 System.out.println("????");
	    	 	UserDetails userDetails =userDetailServiceToken.loadUserByUsername(username);
	    	 	
	    	 	if(jwtService.validateToken(token, userDetails)) {
	    	 		UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
	    	 		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authToken);
	    	 	}
	        }
		filterChain.doFilter(request, response);
	}

}
