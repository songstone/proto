package com.example.proto.dto;

import jakarta.validation.constraints.NotNull;

public record RegisterDepartmentRequest(
    @NotNull(message = "직원 고유번호는 필수 값 입니다.")
    Long en,

    @NotNull(message = "부서 고유번호는 필수 값 입니다.")
    Long dn
) {
}
