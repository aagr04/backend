package com.employeedept.api.service.impl;

import com.employeedept.api.dto.EmployeeDto;
import com.employeedept.api.dto.HighestSalaryEmployeeDTO;
import com.employeedept.api.dto.YoungestEmployeeDTO;
import com.employeedept.api.entity.Employee;
import com.employeedept.api.exception.GeneralException;
import com.employeedept.api.repository.DepartmentRepository;
import com.employeedept.api.repository.EmployeeRepository;
import com.employeedept.api.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Comparator;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(
            EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository
    ) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Employee save(EmployeeDto employeeDto) {

        if (employeeDto.employeeName() == null || employeeDto.employeeName().isBlank()) {
            throw new GeneralException(
                    "Employee name is required",
                    HttpStatus.BAD_REQUEST
            );
        }

        if (employeeDto.employeeLastName() == null || employeeDto.employeeLastName().isBlank()) {
            throw new GeneralException(
                    "Employee lastname is required",
                    HttpStatus.BAD_REQUEST
            );
        }

        if (employeeDto.age() < 18 || employeeDto.age() > 70) {
            throw new GeneralException(
                    "Employee age must be between 18 and 70",
                    HttpStatus.BAD_REQUEST
            );
        }

        if (employeeDto.salary() != null && employeeDto.salary().signum() < 0) {
            throw new GeneralException(
                    "Salary cannot be negative",
                    HttpStatus.BAD_REQUEST
            );
        }

        if (employeeDto.initDate() != null && employeeDto.endDate() != null
                && employeeDto.endDate().isBefore(employeeDto.initDate())) {

            throw new GeneralException(
                    "End date cannot be before start date",
                    HttpStatus.BAD_REQUEST
            );
        }

        departmentRepository.findById(employeeDto.departmentId())
                .filter(d -> "A".equals(d.getDepartmentStatus()))
                .orElseThrow(
                        () -> new GeneralException(
                                "Department is not active",
                                HttpStatus.BAD_REQUEST)
                );

        if (!departmentRepository.existsById(employeeDto.departmentId())) {
            throw new GeneralException(
                    "Department with ID " + employeeDto.departmentId() + " not found",
                    HttpStatus.BAD_REQUEST
            );
        }

        return employeeRepository.save(
                new Employee(
                        employeeDto.departmentId(),
                        employeeDto.employeeName(),
                        employeeDto.employeeLastName(),
                        employeeDto.age(),
                        employeeDto.salary(),
                        employeeDto.initDate(),
                        employeeDto.endDate(),
                        employeeDto.employeeStatus().toUpperCase()
                )
        );
    }

    public void delete(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new GeneralException(
                        "Employee with ID " + id + " not found",
                        HttpStatus.BAD_REQUEST
                ));

        if ("I".equals(employee.getEmployeeStatus())) {
            throw new GeneralException(
                    "Employee is already inactive",
                    HttpStatus.BAD_REQUEST
            );
        }

        employee.setEmployeeStatus("I");
        employeeRepository.save(employee);
    }

    public HighestSalaryEmployeeDTO getHighSalary() {
        return employeeRepository.findAll().stream()
                .filter(e -> e.getSalary() != null)
                .max(Comparator.comparing(Employee::getSalary))
                .map(e -> new HighestSalaryEmployeeDTO(
                        e.getEmployeeName(), e.getSalary())
                )
                .orElseThrow(
                        () -> new GeneralException(
                                "No employees with non-null salary found",
                                HttpStatus.NOT_FOUND
                        ));
    }

    public YoungestEmployeeDTO getYoungestEmployee() {
        return employeeRepository.findAll().stream()
                .min(Comparator.comparing(Employee::getAge))
                .map(e -> new YoungestEmployeeDTO(e.getEmployeeName(), e.getAge()))
                .orElseThrow(
                        () -> new GeneralException(
                                "No employees found", HttpStatus.NOT_FOUND
                        )
                );
    }

    public long countEmployeesHiredLastMonth() {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        return employeeRepository.findAll().stream()
                .filter(e -> e.getInitDate() != null && !e.getInitDate().isBefore(oneMonthAgo))
                .count();
    }

}
