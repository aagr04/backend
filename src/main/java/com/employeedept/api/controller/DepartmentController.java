package com.employeedept.api.controller;

import com.employeedept.api.dto.DepartmentDto;
import com.employeedept.api.entity.Department;
import com.employeedept.api.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(
            DepartmentService departmentService
    ) {
        this.departmentService = departmentService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Department save(@RequestBody DepartmentDto department) {
        return departmentService.save(department);
    }

    @DeleteMapping("/delete/{departmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable int departmentId) {
        departmentService.delete(departmentId);
    }

}
