package com.oracle.emstest.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.oracle.emstest.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{

	
}
