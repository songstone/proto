package com.example.proto.dto;

import com.example.proto.domain.Employee;

import java.time.LocalDateTime;

public record EmployeeDto(
    Long no,
    String email,
    String password,
    String name,
    String phone,
    LocalDateTime createdAt
) {

    public static EmployeeDto fromEntity(Employee employee) {
        return new EmployeeDto(
            employee.getId(),
            employee.getEmail(),
            employee.getPassword(),
            employee.getName(),
            employee.getPhone(),
            employee.getCreatedAt()
        );
    }
}
