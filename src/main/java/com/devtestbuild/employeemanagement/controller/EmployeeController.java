package com.devtestbuild.employeemanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Welcome to DevTestBuildClass Employee Management Application";
    }
}