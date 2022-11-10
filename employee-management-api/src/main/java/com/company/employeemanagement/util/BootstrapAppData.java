package com.company.employeemanagement.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.company.employeemanagement.entity.Employee;
import com.company.employeemanagement.entity.Role;
import com.company.employeemanagement.entity.User;
import com.company.employeemanagement.repository.EmployeeRepository;
import com.company.employeemanagement.repository.UserRepository;

@Component
public class BootstrapAppData {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@EventListener(ApplicationReadyEvent.class)
	public void insertEmployeeData(ApplicationReadyEvent event) {
		
		// Creating Dummy data in database
		Employee employee = new Employee();
		employee.setFirstName("Piyush");
		employee.setLastName("Saxena");
		employee.setDepartment("Marketing");
		employee.setEmail(employee.getFirstName()+"@"+employee.getLastName()+".com");
		
		Employee employee1 = new Employee();
		employee1.setFirstName("Ananya");
		employee1.setLastName("Sharma");
		employee1.setDepartment("Commercial");
		employee1.setEmail(employee1.getFirstName()+"@"+employee1.getLastName()+".com");
		
		Employee employee2 = new Employee();
		employee2.setFirstName("Hitarth");
		employee2.setLastName("Sharma");
		employee2.setDepartment("Technical");
		employee2.setEmail(employee2.getFirstName()+"@"+employee2.getLastName()+".com");
		
		this.employeeRepository.save(employee);
		this.employeeRepository.save(employee1);
		this.employeeRepository.save(employee2);
		
	}

	
	@EventListener(ApplicationReadyEvent.class)
	public void insertRolesData(ApplicationReadyEvent event) {
		
		// Creating application ready users in database
		User ananya = new User();
		ananya.setUsername("ananya");
		ananya.setPassword(this.passwordEncoder.encode("ananya"));
		
		User hitarth = new User();
		hitarth.setUsername("hitarth");
		hitarth.setPassword(this.passwordEncoder.encode("hitarth"));
		
		Role userRole = new Role();
		userRole.setName("ROLE_USER");
		
		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");		

		ananya.addRole(adminRole);
		hitarth.addRole(userRole);
		
		this.userRepository.save(ananya);
		this.userRepository.save(hitarth);
		
	}


}
