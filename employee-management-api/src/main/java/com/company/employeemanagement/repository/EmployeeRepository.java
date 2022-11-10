package com.company.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.company.employeemanagement.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	public List<Employee> findByDepartment(String department);
	
	@Query("select e from Employee e where e.firstName = ?1")	
	public List<Employee> findByFirstName(String firstName);

}
