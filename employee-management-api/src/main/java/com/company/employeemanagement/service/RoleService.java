package com.company.employeemanagement.service;

import java.util.List;

import com.company.employeemanagement.entity.Role;

public interface RoleService {
	
	public Role save(Role role);
	
	public List<Role> findAll();
	
	public Role findById(long id);
	
	public String deleteRoleById(long id);
	
//	public Role addUserToRole(long roleId, long userId);

}
