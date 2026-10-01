package com.devtestbuild.employeemanagement.service;

import com.devtestbuild.employeemanagement.entity.Department;
import com.devtestbuild.employeemanagement.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department saveDepartment(Department department) {

        if (departmentRepository.existsByNameIgnoreCase(department.getName())) {
            throw new IllegalArgumentException(
                    "Department already exists: " + department.getName()
            );
        }

        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department updateDepartment(Long id, Department departmentDetails) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found with id: " + id));

        Optional<Department> existingDepartment =
                departmentRepository.findByNameIgnoreCase(
                        departmentDetails.getName()
                );

        if (existingDepartment.isPresent()
                && !existingDepartment.get().getId().equals(id)) {

            throw new IllegalArgumentException(
                    "Another department already exists with name: "
                            + departmentDetails.getName()
            );
        }

        department.setName(departmentDetails.getName());
        department.setDescription(departmentDetails.getDescription());
        department.setStatus(departmentDetails.getStatus());

        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {

        if (!departmentRepository.existsById(id)) {
            throw new RuntimeException(
                    "Department not found with id: " + id
            );
        }

        departmentRepository.deleteById(id);
    }

    public long getDepartmentCount() {
        return departmentRepository.count();
    }
}