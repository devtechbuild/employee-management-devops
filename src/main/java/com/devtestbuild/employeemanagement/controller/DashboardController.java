package com.devtestbuild.employeemanagement.controller;

import com.devtestbuild.employeemanagement.service.DepartmentService;
import com.devtestbuild.employeemanagement.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public DashboardController(
            EmployeeService employeeService,
            DepartmentService departmentService) {

        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        long totalEmployees =
                employeeService.getEmployeeCount();

        long activeEmployees =
                employeeService.getActiveEmployeeCount();

        long inactiveEmployees =
                employeeService.getInactiveEmployeeCount();

        long totalDepartments =
                departmentService.getDepartmentCount();

        model.addAttribute("totalEmployees", totalEmployees);
        model.addAttribute("activeEmployees", activeEmployees);
        model.addAttribute("inactiveEmployees", inactiveEmployees);
        model.addAttribute("totalDepartments", totalDepartments);

        return "dashboard";
    }
}