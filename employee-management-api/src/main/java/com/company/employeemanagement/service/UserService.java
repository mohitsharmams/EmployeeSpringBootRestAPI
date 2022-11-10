package com.company.employeemanagement.service;

import java.util.List;

import com.company.employeemanagement.entity.User;

public interface UserService {
	
	public User save(User user);
	
	public List<User> findAll();
	
	public User findById(long id);
	
	public String deleteUserById(long id);
	
	public List<User> findUserByRoleId(long id);
	
	public User addRoleToUser(long userId, long roleId);

}
