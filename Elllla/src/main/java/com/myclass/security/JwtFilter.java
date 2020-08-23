package com.myclass.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JwtFilter extends BasicAuthenticationFilter{


	private UserDetailsService userDetailService;

	public JwtFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
		
		super(authenticationManager);
		this.userDetailService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String tokenHeader = request.getHeader("Authorization");
		if(tokenHeader !=null && tokenHeader.startsWith("Bearer ")) {
			String token = tokenHeader.replace("Bearer ", "");
			
			String email = Jwts
					.parser()
					.setSigningKey("abcd")
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
			
			UserDetails userDetail = this.userDetailService.loadUserByUsername(email);
			
			Authentication authentication = 
					new UsernamePasswordAuthenticationToken(userDetail,null ,userDetail.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			chain.doFilter(request, response);
		} else {
			response.sendError(401, "Authenticate fail");
		}
	}
}
