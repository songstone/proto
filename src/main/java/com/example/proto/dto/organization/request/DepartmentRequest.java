package com.example.proto.dto.organization.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DepartmentRequest {

    public record create(
        @NotBlank(message = "부서명은 필수 값 입니다.")
        String name,

        Integer parentNo
    ) {}

    public record leader(
        @NotNull(message = "부서 고유번호는 필수 값입니다.")
        Integer departmentNo,

        @NotNull(message = "직원 고유번호는 필수 값 입니다.")
        Integer employeeNo
    ) {}
}
