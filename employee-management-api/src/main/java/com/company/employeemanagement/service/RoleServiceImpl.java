package com.company.employeemanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.employeemanagement.entity.Role;
import com.company.employeemanagement.repository.RoleRepository;
import com.company.employeemanagement.repository.UserRepository;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Role save(Role role) {
		Role savedRole = this.roleRepository.save(role);
		return savedRole;
	}

	@Override
	public List<Role> findAll() {
		List<Role> roles = this.roleRepository.findAll();
		return roles;
	}

	@Override
	public Role findById(long id) {
		Optional<Role> optionalRole = this.roleRepository.findById(id);
		if(optionalRole.isPresent()) {
			return optionalRole.get();
		}
		else {
			throw new IllegalArgumentException("invalid role id");
		}
	}

	@Override
	public String deleteRoleById(long id) {
		if(this.roleRepository.existsById(id)) {
			this.roleRepository.deleteById(id);
			return ("Deleted Role with id - "+id);
		}
		else {
			throw new IllegalArgumentException("invalid role id");
		}
	}

//	@Override
//	public Role addUserToRole(long roleId, long userId) {
//		Role role = this.roleRepository.findById(roleId).get();
//		User user = this.userRepository.findById(userId).get();
//		role.addUser(user);
//		return roleRepository.save(role);
//	}

}
