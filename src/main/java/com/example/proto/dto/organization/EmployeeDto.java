package com.example.proto.dto.organization;

import com.example.proto.domain.organization.employee.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record EmployeeDto (
    Integer no,
    String email,
    String name,
    String phone,
    List<DepartmentDto> departments,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt
) {
    public static EmployeeDto of(Employee employee) {
        return new EmployeeDto(
            employee.getIdx(),
            employee.getEmail(),
            employee.getName(),
            employee.getPhone(),
            employee.getEmployeeDepartments().stream().map(
                employeeDepartment -> DepartmentDto.of(employeeDepartment.getDepartment())
            ).toList(),
            employee.getRegisterDate()
        );
    }
}
