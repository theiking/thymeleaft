package com.myclass.service;

import java.util.List;

import com.myclass.dto.RoleDto;

public interface RoleService {
	List<RoleDto> findAll();
	void saveOrUpdate(RoleDto dto);
	void add(RoleDto dto);
	void delete(int id);
	RoleDto findById(int id);
}
