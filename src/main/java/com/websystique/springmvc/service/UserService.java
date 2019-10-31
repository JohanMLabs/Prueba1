package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.User;


public interface UserService {
	
	User findById(int id);
	
	User findByUSU(String usu);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserByUSU(String usu);

	List<User> findAllUsers(); 
	
	boolean isUserUSUUnique(Integer id, String usu);

}