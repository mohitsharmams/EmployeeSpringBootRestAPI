package com.company.employeemanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.employeemanagement.entity.Role;
import com.company.employeemanagement.entity.User;
import com.company.employeemanagement.repository.RoleRepository;
import com.company.employeemanagement.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
 
	@Autowired
	MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired RoleRepository roleRepository;

	@Override
	public User save(User user) {
		String password = user.getPassword();
		user.setPassword(this.passwordEncoder.encode(password));
		User savedUser = this.userRepository.save(user);
		return savedUser;
	}

	@Override
	public List<User> findAll() {
		List<User> users = this.userRepository.findAll();
		return users;
	}

	@Override
	public User findById(long id) {
		Optional<User> optionalUser = this.userRepository.findById(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}
		else {
			throw new IllegalArgumentException("invalid user id");
		}
	}

	@Override
	public String deleteUserById(long id) {
		if(this.userRepository.existsById(id)) {
			this.userRepository.deleteById(id);
			return ("Deleted User with id - "+id);
		}
		else {
			throw new IllegalArgumentException("invalid user id");
		}
	}
	
	@Override
	public List<User> findUserByRoleId(long id) {
		if(this.roleRepository.existsById(id)) {
			return this.userRepository.findUsersByRolesId(id);
		}
		else {
			throw new IllegalArgumentException("invalid role id");
		}
		
	}
	// Dynamically assign authorization role to user
	@Override
	public User addRoleToUser(long userId, long roleId) {
		User user = this.userRepository.findById(userId).get();
		Role role = this.roleRepository.findById(roleId).get();
		user.addRole(role);
		this.userRepository.save(user);
		myUserDetailsService.loadUserByUsername(user.getUsername());
		return user;
	}

}
