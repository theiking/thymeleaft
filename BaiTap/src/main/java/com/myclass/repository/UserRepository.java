package com.myclass.repository;

import java.util.List;

import com.myclass.entity.User;

public interface UserRepository {
	
	public List<User> findAll();

	public User findById(int id);

	public void saveOrUpdate(User user);

	public void delete(int id);

	public List<User> search(String keyword);
}
