package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.UserDto;
import com.myclass.entity.User;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserService;

@Service

@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements UserService  {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<UserDto> getAll() {
//		List<User> users = userRepository.findAll();
//		List<UserDto> dtos = new ArrayList<UserDto>();
//		
//		for(User user : users) {
//			UserDto uDto = new UserDto();
//			uDto.setId(user.getId());
//			uDto.setFullname(user.getFullname());
//			uDto.setEmail(user.getEmail());
//			uDto.setRoleName(user.getRole().getDescription());
//			
//			dtos.add(uDto);
//		}
		return userRepository.findAllWithRole();
	}

	@Override
	public void saveOrUpdate(UserDto dto) {
		User user = new User();
		user.setFullname(dto.getFullname());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setRoleId(dto.getRoleId());
		
		
		
		userRepository.saveOrUpdate(user);
	}

	@Override
	public void add(UserDto dto) {
		
	}

	@Override
	public void delete(int id) {
		
	}

	@Override
	public UserDto findById(int id) {
		return null;
	}
	
	
	
	
	
	
}
