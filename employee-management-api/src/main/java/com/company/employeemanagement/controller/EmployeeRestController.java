package com.company.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.employeemanagement.entity.Employee;
import com.company.employeemanagement.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping
	public List<Employee> fetchAllEmployees(){
		return this.employeeService.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee fetchEmployeeById(@PathVariable("id") long id) {	
		return this.employeeService.findById(id);
	}
	
	@GetMapping("/department")
	public List<Employee> fetchEmployeeByDepartment(@RequestParam(name = "department", required = false, defaultValue = "department") String department) {	
		return this.employeeService.findByDepartment(department);
	}
	
	@PostMapping
	public Employee saveEmployee(@RequestBody Employee employee) {
		return this.employeeService.save(employee);
	}
	
	@PutMapping
	public Employee updateEmployee(@RequestBody Employee employee) {
		return this.employeeService.updateEmployee(employee);
	}
	
	@DeleteMapping("/{id}")
	public String deleteEmployeeById(@PathVariable("id") long id) {
		return this.employeeService.deleteEmployeeById(id);
	}
	
	@GetMapping("/sort/id")
	public List<Employee> CustomSortEmployeeById(@RequestParam Direction order){
		return employeeService.customSortEmployeeById(order);
	}
	
	@GetMapping("/sort/firstname")
	public List<Employee> CustomeSortEmployeeByName(@RequestParam(name = "order", defaultValue = "ASC") Direction order){
		return employeeService.customSortEmployeeByName(order);
	}
	
	@GetMapping("/search/{firstname}")
	public List<Employee> searchEmployeesByfirstName(@PathVariable("firstname") String firstName){
		return employeeService.findByFirstName(firstName);
	}

}
