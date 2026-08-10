package com.oracle.emstest.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.emstest.entity.Employee;
import com.oracle.emstest.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
@PostMapping
 public String employeeRegister(@Valid @RequestBody Employee employee) {
return 	employeeService.registerEmployee(employee);
}

@GetMapping("/{email}")
public Object getEmployeeByEmail(@PathVariable String email) {
	return employeeService.getEmployeeByEmail(email);
}

@GetMapping
public Object getAllEmployee() {
	 return employeeService.getAllEmployee();
}

@DeleteMapping("/{email}")
public Object deleteEmployeeByEmail(@PathVariable	String email) {
	return employeeService.deleteEmployeeByEmail(email);
}

@PatchMapping("/{email}")
public Object updateEmployee(@PathVariable String email,@RequestBody Employee employees) {
return employeeService.updateEmployee(email, employees);
}

}
 	
