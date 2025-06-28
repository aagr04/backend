package com.employeedept.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DepartmentDto(
        @JsonProperty("department_name")
        String departmentName,
        @JsonProperty("department_status")
        String departmentStatus
) { }
