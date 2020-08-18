package com.myclass.repository;

import java.util.List;

import com.myclass.entity.Role;

public interface RoleRepository extends GenericRepository<Role, Integer> {
	
	public List<Role> search(String keyword);
}
