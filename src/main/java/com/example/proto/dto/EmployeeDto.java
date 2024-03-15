package com.example.proto.dto;

import com.example.proto.domain.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record EmployeeDto(
    Long no,
    String email,
    String name,
    String phone,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt
) {

    public static EmployeeDto fromEntity(Employee employee) {
        return new EmployeeDto(
            employee.getId(),
            employee.getEmail(),
            employee.getName(),
            employee.getPhone(),
            employee.getCreatedAt()
        );
    }
}
