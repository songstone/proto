package com.example.proto.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
    @NotNull(message = "이메일은 필수 값 입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    String email,

    @NotNull(message = "비밀번호는 필수 값 입니다.")
    String password
) {
}
