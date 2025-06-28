package com.employeedept.api.service;

import com.employeedept.api.dto.EmployeeDto;
import com.employeedept.api.dto.HighestSalaryEmployeeDTO;
import com.employeedept.api.dto.YoungestEmployeeDTO;
import com.employeedept.api.entity.Employee;

public interface EmployeeService {

    Employee save(EmployeeDto employeeDto);
    void delete(int id);
    HighestSalaryEmployeeDTO getHighSalary();
    YoungestEmployeeDTO getYoungestEmployee();
    public long countEmployeesHiredLastMonth();

}
