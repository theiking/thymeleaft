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

import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;

@Repository
@Transactional(rollbackOn = Exception.class)
public class RoleRepositoryImpl implements RoleRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Role> findAll() {

		List<Role> list = new ArrayList<Role>();

		// TẠO ĐỐI TƯỢNG SESSION
		Session session = sessionFactory.getCurrentSession();
		try {
			// MỞ KẾT NỐI VÀ THỰC THI TRUY VẤN
			Query<Role> query = session.createQuery("FROM Role", Role.class);
			list = query.getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Role findById(int id) {

		// TẠO ĐỐI TƯỢNG SESSION
		Session session = sessionFactory.getCurrentSession();
		try {
			// MỞ KẾT NỐI VÀ THỰC THI TRUY VẤN
			Role role = session.find(Role.class, id);
			return role;
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Role> findByName(String name) {
		List<Role> list = new ArrayList<Role>();
		// TẠO ĐỐI TƯỢNG SESSION
		Session session = sessionFactory.getCurrentSession();
		try {
			// MỞ KẾT NỐI VÀ THỰC THI TRUY VẤN
			Query<Role> query = session.createQuery("FROM Role WHERE name = :name", Role.class);
			query.setParameter("name", name);
			list = query.getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void saveOrUpdate(Role role) {
		// TẠO ĐỐI TƯỢNG SESSION
		Session session = sessionFactory.getCurrentSession();
		try {
			// MỞ KẾT NỐI VÀ THỰC THI TRUY VẤN
			session.saveOrUpdate(role);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
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

	public List<Role> search(String keyword) {
		List<Role> list = new ArrayList<Role>();
		// TẠO ĐỐI TƯỢNG SESSION
		Session session = sessionFactory.getCurrentSession();
		String queryHQL = "FROM Role WHERE name LIKE :keyword or description LIKE :keyword";
		try {
			// MỞ KẾT NỐI VÀ THỰC THI TRUY VẤN
			Query<Role> query = session.createQuery(queryHQL, Role.class);
			query.setParameter("keyword", "%" + keyword + "%");
			list = query.getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return list;
	}
}
