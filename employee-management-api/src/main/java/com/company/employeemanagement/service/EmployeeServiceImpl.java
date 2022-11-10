package com.company.employeemanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.company.employeemanagement.entity.Employee;
import com.company.employeemanagement.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee save(Employee employee) {
		// here "savedEmployee" will have the id, while "employee" will not have any id
		// because we are auto generating the id
		Employee savedEmployee = this.employeeRepository.save(employee);
		return savedEmployee;
	}

	@Override
	public List<Employee> findAll() {
		List<Employee> employees = this.employeeRepository.findAll();
		return employees;
	}

	@Override
	public Employee findById(long id) {
		Optional<Employee> optionalEmployee = this.employeeRepository.findById(id);
		if (optionalEmployee.isPresent()) {
			return optionalEmployee.get();
		} 
		else {
			throw new IllegalArgumentException("invalid employee id");
		}
	}
	
	@Override
	public Employee updateEmployee(Employee employee) {
		Optional<Employee> existingEmployee = this.employeeRepository.findById(employee.getId());
		if(existingEmployee.isPresent()) {
			existingEmployee.get().setFirstName(employee.getFirstName());
			existingEmployee.get().setLastName(employee.getLastName());
			existingEmployee.get().setEmail(employee.getEmail());
			existingEmployee.get().setDepartment(employee.getDepartment());
			this.employeeRepository.save(existingEmployee.get());
			return existingEmployee.get();
		}
		else {
			throw new IllegalArgumentException("invalid employee");
		}
	}

	@Override
	public String deleteEmployeeById(long id) {
		if (this.employeeRepository.existsById(id)) {
			this.employeeRepository.deleteById(id);
			return ("Deleted Employee with id - "+id);
		} else {
			throw new IllegalArgumentException("invalid employee id");
		}
	}

	@Override
	public List<Employee> findByDepartment(String department) {
		
		return this.employeeRepository.findByDepartment(department);

	}

	@Override
	public List<Employee> customSortEmployeeById(Direction direction) {
		
		return this.employeeRepository.findAll(Sort.by(direction, "id"));
		
	}
	
	@Override
	public List<Employee> customSortEmployeeByName(Direction direction) {

		return this.employeeRepository.findAll(Sort.by(direction, "firstName"));

	}

	@Override
	public List<Employee> findByFirstName(String firstName) {
		return this.employeeRepository.findByFirstName(firstName);
	}


}
