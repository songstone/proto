package com.example.proto.dto.organization;

import com.example.proto.domain.organization.department.Department;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record DepartmentDto (
    Integer no,
    String name,
    List<DepartmentDto> childDepartments,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt
) {
    public static DepartmentDto of(Department department) {
        return new DepartmentDto (
            department.getIdx(),
            department.getName(),
            department.getChildDepartments().stream()
                .map(DepartmentDto::of)
                    .toList(),
            department.getRegisterDate()
        );
    }
}
