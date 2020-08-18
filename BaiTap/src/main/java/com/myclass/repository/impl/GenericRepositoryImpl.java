package com.myclass.repository.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.myclass.entity.Role;
import com.myclass.repository.GenericRepository;

public abstract class GenericRepositoryImpl<T, K> implements GenericRepository<T, K> {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Class<? extends T> clazz;
	
	public GenericRepositoryImpl() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) type;
		clazz = (Class) pt.getActualTypeArguments()[0];
	}

	public List<T> findAll() {


		// TẠO ĐỐI TƯỢNG SESSION
		Session session = sessionFactory.getCurrentSession();
		try {
			// MỞ KẾT NỐI VÀ THỰC THI TRUY VẤN
			Query<T> query = (Query<T>) session.createQuery("FROM Role", clazz);
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	public T findById(K id) {

		// TẠO ĐỐI TƯỢNG SESSION
		Session session = sessionFactory.getCurrentSession();
		try {
			// MỞ KẾT NỐI VÀ THỰC THI TRUY VẤN
			Role role = session.find(Role.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return null;
	}

	

	public void saveOrUpdate(T role) {
		// TẠO ĐỐI TƯỢNG SESSION
		Session session = sessionFactory.getCurrentSession();
		try {
			// MỞ KẾT NỐI VÀ THỰC THI TRUY VẤN
			session.saveOrUpdate(role);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public void delete(K id) {
		// TẠO ĐỐI TƯỢNG SESSION
		Session session = sessionFactory.getCurrentSession();
		try {
			// MỞ KẾT NỐI VÀ THỰC THI TRUY VẤN
			Role role = session.find(Role.class, id);
			if (role != null) {
				session.remove(role);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

}
