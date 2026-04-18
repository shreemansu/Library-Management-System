package com.qsp.dao;

import java.util.List;

import com.qsp.entity.User;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public final class UserDao extends AbstractDao {
	private static UserDao object=new UserDao();
	
	private UserDao() {
		
	}
	
	public static UserDao getInstance() {
		return object;
	}
	
	public String addUser(String username,String password,String role) {
		if(username==null || username.isEmpty()) return "Enter Username";
		if(password==null|| password.isEmpty()) return "Enter Password";
		if(role==null || role.isEmpty()) return "Enter Role";
		User user=new User(null,username,password,role);
		try {
			EntityTransaction et=em.getTransaction(); 
			et.begin();
			em.merge(user);
			et.commit();
			return "User Saved Successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Exception Occured"; 
		}
	}
	public List<User> getUsersByUserName(String uname){
		String hql="select u from User u where u.username = :uname ";
		Query query=em.createQuery(hql);
		query.setParameter("uname", uname);
		List<User> users=query.getResultList();
		return users;
	}
}
