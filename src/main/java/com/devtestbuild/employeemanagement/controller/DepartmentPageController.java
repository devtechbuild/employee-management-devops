package com.devtestbuild.employeemanagement.controller;

import com.devtestbuild.employeemanagement.entity.Department;
import com.devtestbuild.employeemanagement.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DepartmentPageController {

    private final DepartmentService departmentService;

    public DepartmentPageController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public String departments(Model model) {

        model.addAttribute(
                "departments",
                departmentService.getAllDepartments()
        );

        return "departments";
    }

    @GetMapping("/departments/add")
    public String addDepartmentForm(Model model) {

        model.addAttribute("department", new Department());

        return "add-department";
    }

    @PostMapping("/departments/add")
    public String saveDepartment(
            @ModelAttribute Department department) {

        departmentService.saveDepartment(department);

        return "redirect:/departments";
    }

    @GetMapping("/departments/edit/{id}")
    public String editDepartment(
            @PathVariable Long id,
            Model model) {

        Department department = departmentService
                .getDepartmentById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Department not found with id: " + id
                        ));

        model.addAttribute("department", department);

        return "edit-department";
    }

    @PostMapping("/departments/edit/{id}")
    public String updateDepartment(
            @PathVariable Long id,
            @ModelAttribute Department department) {

        departmentService.updateDepartment(id, department);

        return "redirect:/departments";
    }

    @PostMapping("/departments/delete/{id}")
    public String deleteDepartment(
            @PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return "redirect:/departments";
    }
}