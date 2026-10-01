package com.devtestbuild.employeemanagement.repository;

import com.devtestbuild.employeemanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    long countByStatus(String status);
}