package com.employeedept.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeeDto(
        @JsonProperty("department_id")
        int departmentId,
        @JsonProperty("employee_name")
        String employeeName,
        @JsonProperty("employee_last_name")
        String employeeLastName,
        @JsonProperty("age")
        int age,
        @JsonProperty("salary")
        BigDecimal salary,
        @JsonProperty("init_date")
        LocalDate initDate,
        @JsonProperty("end_date")
        LocalDate endDate,
        @JsonProperty("employee_status")
        String employeeStatus
) { }
