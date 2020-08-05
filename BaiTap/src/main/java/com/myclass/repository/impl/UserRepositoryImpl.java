package com.myclass.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclass.entity.User;
import com.myclass.repository.UserRepository;

@Repository
@Transactional(rollbackOn = Exception.class)
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public List<User> findAll() {

		List<User> list = new ArrayList<User>();

		// TẠO ĐỐI TƯỢNG SESSION
		Session session = sessionFactory.getCurrentSession();
		try {
			// MỞ KẾT NỐI VÀ THỰC THI TRUY VẤN
			Query<User> query = session.createQuery("FROM User", User.class);
			list = query.getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return list;
	}

	public User findById(int id) {
		// TẠO ĐỐI TƯỢNG SESSION
		Session session = sessionFactory.getCurrentSession();
		try {
			// MỞ KẾT NỐI VÀ THỰC THI TRUY VẤN
			User user = session.find(User.class, id);
			return user;
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public void saveOrUpdate(User user) {
		// TẠO ĐỐI TƯỢNG SESSION
		Session session = sessionFactory.getCurrentSession();
		try {
			// MỞ KẾT NỐI VÀ THỰC THI TRUY VẤN
			session.saveOrUpdate(user);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		// TẠO ĐỐI TƯỢNG SESSION
		Session session = sessionFactory.getCurrentSession();
		try {
			// MỞ KẾT NỐI VÀ THỰC THI TRUY VẤN
			User user = session.find(User.class, id);
			if (user != null) {
				session.remove(user);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public List<User> search(String keyword) {
		List<User> list = new ArrayList<User>();
		// TẠO ĐỐI TƯỢNG SESSION
		Session session = sessionFactory.getCurrentSession();
		String queryHQL = "FROM User WHERE fullname LIKE :keyword or email LIKE :keyword";
		try {
			// MỞ KẾT NỐI VÀ THỰC THI TRUY VẤN
			Query<User> query = session.createQuery(queryHQL, User.class);
			query.setParameter("keyword", "%" + keyword + "%");
			list = query.getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return list;
	}
}