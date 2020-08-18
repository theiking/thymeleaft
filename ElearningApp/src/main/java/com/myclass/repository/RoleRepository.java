package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	//	@Query("SELECT r FROM Role r WHERE name like CONCAT('%',:keyword,'%') OR description like CONCAT('%',:keyword,'%')")
	@Query("SELECT r FROM Role r WHERE name like '%'||:keyword||'%' OR description like '%'||:keyword||'%'")
	public List<Role> search(@Param("keyword") String keyword);
	
	
}
