package com.company.employeemanagement.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.company.employeemanagement.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	


}
