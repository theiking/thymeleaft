package com.myclass.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.myclass.security.JwtFilter;


@Configuration
@EnableWebSecurity
@ComponentScan("com.myclass")
public class ApiSercurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private UserDetailsService userDetailsService;

	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.antMatcher("/api/**")
		.authorizeRequests()
		.antMatchers("/api/auth/login")
		.permitAll()
		.antMatchers("/api/role/**")
		.hasAnyRole("ADMIN")
		.antMatchers("/api/user/**")
		.hasAnyRole("USER")
		.anyRequest()
		.authenticated();
		
		http.addFilter(new JwtFilter(authenticationManager(),userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	
	
}
