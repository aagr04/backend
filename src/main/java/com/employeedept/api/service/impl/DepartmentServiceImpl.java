package com.employeedept.api.service.impl;

import com.employeedept.api.dto.DepartmentDto;
import com.employeedept.api.entity.Department;
import com.employeedept.api.exception.GeneralException;
import com.employeedept.api.repository.DepartmentRepository;
import com.employeedept.api.repository.EmployeeRepository;
import com.employeedept.api.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(
            EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository
    ) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Department save(DepartmentDto department) {

        if (department.departmentName() == null || department.departmentName().isBlank()) {
            throw new GeneralException(
                    "Department name is required",
                    HttpStatus.BAD_REQUEST
            );
        }

        if (!"A".equalsIgnoreCase(department.departmentStatus()) && !"I".equalsIgnoreCase(department.departmentStatus())) {
            throw new GeneralException(
                    "Department status must be 'A' (active) or 'I' (inactive)",
                    HttpStatus.BAD_REQUEST
            );
        }

        return departmentRepository.save(
                new Department(
                        department.departmentName(),
                        department.departmentStatus().toUpperCase()
                )
        );
    }

    public void delete(int departmentId) {

        if (employeeRepository.existsByDepartmentIdAndEmployeeStatus(
                departmentId, "A")
        ) {
            throw new GeneralException(
                    "Cannot deactivate department with active employees",
                    HttpStatus.CONFLICT
            );
        }

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(
                        () -> new GeneralException(
                        "Department with ID " + departmentId + " not found",
                        HttpStatus.BAD_REQUEST
                ));

        if ("I".equalsIgnoreCase(department.getDepartmentStatus())) {
            throw new GeneralException(
                    "Department is already inactive",
                    HttpStatus.BAD_REQUEST
            );
        }

        department.setDepartmentStatus("I");
        departmentRepository.save(department);
    }

}
