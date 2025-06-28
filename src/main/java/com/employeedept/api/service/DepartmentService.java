package com.employeedept.api.service;

import com.employeedept.api.dto.DepartmentDto;
import com.employeedept.api.entity.Department;

public interface DepartmentService {

    Department save(DepartmentDto department);
    void delete(int departmentId);

}
