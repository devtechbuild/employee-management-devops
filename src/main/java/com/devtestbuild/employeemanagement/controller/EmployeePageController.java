package com.devtestbuild.employeemanagement.controller;

import com.devtestbuild.employeemanagement.entity.Employee;
import com.devtestbuild.employeemanagement.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeePageController {

    private final EmployeeService employeeService;

    public EmployeePageController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String employees(Model model) {

        model.addAttribute(
                "employees",
                employeeService.getAllEmployees()
        );

        return "employees";
    }

    // VIEW
    @GetMapping("/employees/view/{id}")
    public String viewEmployee(
            @PathVariable Long id,
            Model model) {

        Employee employee = employeeService
                .getEmployeeById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        model.addAttribute("employee", employee);

        return "view-employee";
    }

    // EDIT PAGE
    @GetMapping("/employees/edit/{id}")
    public String editEmployee(
            @PathVariable Long id,
            Model model) {

        Employee employee = employeeService
                .getEmployeeById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        model.addAttribute("employee", employee);

        return "edit-employee";
    }

    // UPDATE
    @PostMapping("/employees/edit/{id}")
    public String updateEmployee(
            @PathVariable Long id,
            @ModelAttribute Employee employee) {

        employeeService.updateEmployee(id, employee);

        return "redirect:/employees";
    }

    // DELETE
    @PostMapping("/employees/delete/{id}")
    public String deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return "redirect:/employees";
    }
}