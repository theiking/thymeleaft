package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myclass.dto.UserDetailDto;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Email not found!");
		}
		

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		String roleName = user.getRole().getName();
		authorities.add(new SimpleGrantedAuthority(roleName));		
		UserDetailDto dto = new UserDetailDto(user.getEmail(), user.getPassword(), authorities);
				
		return dto;
	}

}
