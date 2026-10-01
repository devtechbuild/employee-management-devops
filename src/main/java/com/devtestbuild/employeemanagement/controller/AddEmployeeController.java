package com.devtestbuild.employeemanagement.controller;

import com.devtestbuild.employeemanagement.entity.Employee;
import com.devtestbuild.employeemanagement.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AddEmployeeController {

    private final EmployeeService employeeService;

    public AddEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/add")
    public String addEmployeeForm() {
        return "add-employee";
    }

    @PostMapping("/employees/add")
    public String saveEmployee(@ModelAttribute Employee employee) {

        employeeService.saveEmployee(employee);

        return "redirect:/employees";
    }
}