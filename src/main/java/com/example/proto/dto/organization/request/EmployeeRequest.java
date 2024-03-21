package com.example.proto.dto.organization.request;

import com.example.proto.domain.organization.employee.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Pageable;

public class EmployeeRequest {
    public record Token (
        @NotBlank(message = "이메일은 필수 값 입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email,

        @NotBlank(message = "비밀번호는 필수 값 입니다.")
        String password
    ) {
    }

    public record list (
        Integer dn,
        Pageable pageable
    ) {
    }
}
