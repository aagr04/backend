package com.employeedept.api.controller;

import com.employeedept.api.dto.EmployeeDto;
import com.employeedept.api.dto.HighestSalaryEmployeeDTO;
import com.employeedept.api.dto.YoungestEmployeeDTO;
import com.employeedept.api.entity.Employee;
import com.employeedept.api.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(
            EmployeeService employeeService
    ){
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee save(@RequestBody EmployeeDto employeeDto){
        return employeeService.save(employeeDto);
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        employeeService.delete(id);
    }

    @GetMapping("/highestSalary")
    @ResponseStatus(HttpStatus.OK)
    public HighestSalaryEmployeeDTO getHighSalary(){
        return employeeService.getHighSalary();
    }

    @GetMapping("/lowerAge")
    @ResponseStatus(HttpStatus.OK)
    public YoungestEmployeeDTO getYoungestEmployee(){
        return employeeService.getYoungestEmployee();
    }

    @GetMapping("/countLastMonth")
    @ResponseStatus(HttpStatus.OK)
    public long countEmployeesLastMonth() {
        return employeeService.countEmployeesHiredLastMonth();
    }

}
