package com.employeedept.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record HighestSalaryEmployeeDTO(
        @JsonProperty("employee_name")
        String employeeName,
        @JsonProperty("salary")
        BigDecimal salary
) {
}
