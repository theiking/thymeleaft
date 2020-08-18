package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.UserDto;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserService;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<UserDto> findAll() {
		List<User> users = userRepository.findAll();
		
		List<UserDto> dtos = new ArrayList<UserDto>();
		for (User user : users) {
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setFullname(user.getFullname());
			userDto.setEmail(user.getEmail());
			
			String desc = user.getRole().getDescription();
			userDto.setRoleDesc(desc);
			
			dtos.add(userDto);
		}
		return dtos;
	}

	public void add(UserDto dto) {
		User user = new User();
		
		user.setEmail(dto.getEmail());
		user.setFullname(dto.getFullname());
		user.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(12)));
		user.setAvatar(dto.getAvatar());
		user.setRoleId(dto.getRoleId());
		
		userRepository.save(user);
	}
	
	public UserDto findById(int id) {
		User user = userRepository.findById(id).get();
		
		return new UserDto(user.getId(), user.getFullname(), user.getEmail(), user.getPassword(), user.getAvatar(),user.getRole().getDescription() ) ;
	}

	public void update(UserDto dto) {
		User user = userRepository.findById(dto.getId()).get();
		user.setEmail(dto.getEmail());
		user.setFullname(dto.getFullname());
		user.setPassword(dto.getPassword());
		user.setAvatar(dto.getAvatar());
		user.setRoleId(dto.getRoleId());
		
		userRepository.save(user);
	}

	public void delete(int id) {
		userRepository.deleteById(id);

	}
	


}
