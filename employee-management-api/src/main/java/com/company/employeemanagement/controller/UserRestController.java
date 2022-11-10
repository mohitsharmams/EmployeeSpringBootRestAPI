package com.company.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.employeemanagement.entity.User;
import com.company.employeemanagement.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public List<User> fetchAllUser(){
		return this.userService.findAll();
	}
	
	@GetMapping("/{id}")
	public User fetchUserById(@PathVariable("id") long id) {
		return this.userService.findById(id);
	}
	
	@GetMapping("/roles/{id}")
	public List<User> fetchRoleByUserId(@PathVariable("id") long id){
		return this.userService.findUserByRoleId(id);
	}
	
	@PostMapping
	public User saveUser(@RequestBody User user) {
		return this.userService.save(user);
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") long id) {
		return this.userService.deleteUserById(id);
	}
	
	@PutMapping("/{userId}/roles/{roleId}")
	User addRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
		return userService.addRoleToUser(userId, roleId);
	}
	
	

	

}
