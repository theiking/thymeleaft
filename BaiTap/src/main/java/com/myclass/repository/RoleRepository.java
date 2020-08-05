package com.myclass.repository;

import java.util.List;

import com.myclass.entity.Role;

public interface RoleRepository {
	public List<Role> findAll();
	public Role findById(int id);
	public void saveOrUpdate(Role role);
	public void delete(int id);
	public List<Role> search(String keyword);
}
