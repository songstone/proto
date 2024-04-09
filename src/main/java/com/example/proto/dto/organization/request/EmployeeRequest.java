package com.example.proto.dto.organization.request;

import com.example.proto.constant.NyStatus;
import com.example.proto.domain.organization.position.Position;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

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
        Integer rankNo,
        String keyword,

        @Range(min = 0, max = 1, message = "활성화여부 검색값이 올바르지 않습니다.")
        Integer isActive
    ) {
    }
}
