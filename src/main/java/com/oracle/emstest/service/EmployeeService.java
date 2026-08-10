package com.oracle.emstest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oracle.emstest.entity.Employee;
import com.oracle.emstest.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	
	private EmployeeRepository employeeRepository;

	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	} 
	

 public String registerEmployee(Employee employee) {
 Optional<Employee> emp=	employeeRepository.findById(employee.getEmail());
 if(emp.isPresent()) {
	 return "Email Already Exist Please Enter Unique Email";
 }
  else {
	 Employee em=new Employee();
	 em.setName(employee.getName());
	 em.setEmail(employee.getEmail());
	 em.setSalary(employee.getSalary());
	 em.setDepartment(employee.getDepartment());
	 
	 employeeRepository.save(em);
	 return "data inserted";
  }
 }
 
 public Object getEmployeeByEmail(String email) {
	 Optional<Employee> empp=	employeeRepository.findById(email);
	 if(empp.isPresent()) {
		 return empp.get();
	 }else {
		 return "No such data is exist with this "+email;
	 }
 }
 
 public Object getAllEmployee() {
	 List<Employee> eep= employeeRepository.findAll();
 if(eep.isEmpty()) {
	return "Data Is Not Present In DataBase";
	}else {
	return eep;
	}
 }
 
 public Object deleteEmployeeByEmail(String email) {
	 Optional<Employee> empp=	employeeRepository.findById(email);
	 if(empp.isPresent()) {
		 employeeRepository.deleteById(email);
		 return "Entered email data is deleted";
	 }else {
		 return "No such data is exist with this "+email;
	 }
 }
 
 public Object updateEmployee(String email, Employee employees) {
	    Optional<Employee> oe = employeeRepository.findById(email);
	    
	    if (oe.isPresent()) {
	        Employee eml = oe.get(); 

	        
	        if (employees.getName() != null) {
	            eml.setName(employees.getName());
	        }
	        if (employees.getSalary() != 0) { 
	            eml.setSalary(employees.getSalary());
	        }
	        if (employees.getDepartment() != null) {
	            eml.setDepartment(employees.getDepartment());
	        }
	        if(employees.getSalary()<0) {
	        	return "cant't assign negative salary"; 
	        }

	        employeeRepository.save(eml); 
	        return "Data Updated";
	    } else {
	        return "No Data To Update";
	    }
	}
}
