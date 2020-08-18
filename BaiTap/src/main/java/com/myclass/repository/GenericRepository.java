package com.myclass.repository;

import java.util.List;


public interface GenericRepository<T, K> {
	List<T> findAll();
	void saveOrUpdate(T dto);
	void delete(K id);
	T findById(K id);
	public List<T> search(String keyword);
	
}
