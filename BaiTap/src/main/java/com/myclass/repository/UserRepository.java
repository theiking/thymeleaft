package com.myclass.repository;

import java.util.List;

import com.myclass.dto.UserDto;
import com.myclass.entity.User;

public interface UserRepository extends GenericRepository<User, Integer> {
	
	
	List<UserDto> findAllWithRole();
}
