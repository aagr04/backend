package com.employeedept.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record YoungestEmployeeDTO(
        @JsonProperty("employee_name")
        String employeeName,
        @JsonProperty("age")
        int age
) {
}
