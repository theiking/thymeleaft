package com.myclass.service;

import java.util.List;


import com.myclass.dto.UserDto;

public interface UserService {
	List<UserDto> getAll();
	void saveOrUpdate(UserDto dto);
	void add(UserDto dto);
	void delete(int id);
	UserDto findById(int id);
}
