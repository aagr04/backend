package com.employeedept.api.repository;

import com.employeedept.api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    boolean existsByDepartmentIdAndEmployeeStatus(int departmentId, String employeeStatus);

}
