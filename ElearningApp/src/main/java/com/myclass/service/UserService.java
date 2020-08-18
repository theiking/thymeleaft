package com.myclass.service;

import java.util.List;

import com.myclass.dto.UserDto;

public interface UserService {
	List<UserDto> findAll();
	void add(UserDto dto);
	UserDto findById(int id);
	void update(UserDto dto);
	void delete(int id);
}
