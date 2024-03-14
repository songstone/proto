package com.example.proto.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
    @NotNull @Email String email,
    @NotNull String password
) {
}
