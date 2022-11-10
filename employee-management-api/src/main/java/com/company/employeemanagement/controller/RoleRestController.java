package com.company.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.employeemanagement.entity.Role;
import com.company.employeemanagement.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleRestController {
	
	@Autowired
	RoleService roleService;
	
	@GetMapping
	public List<Role> fetchAllRoles(){
		return this.roleService.findAll();
	}
	
	@GetMapping("/{id}")
	public Role fetchRoleById(@PathVariable("id") long id) {
		return this.roleService.findById(id);
	}
	
	@PostMapping
	public Role saveRole(@RequestBody Role role) {
		return this.roleService.save(role);
	}
	
	@DeleteMapping("{id}")
	public String deleteRoleById(@PathVariable("id") long id) {
		return this.roleService.deleteRoleById(id);
	}
	
}
