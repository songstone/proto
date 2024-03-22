package com.example.proto.dto.organization.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmployeeRequest {
    public record Token (
        @NotBlank(message = "이메일은 필수 값 입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email,

        @NotBlank(message = "비밀번호는 필수 값 입니다.")
        String password
    ) {
    }

    public record List (

    ) {
    }
}
