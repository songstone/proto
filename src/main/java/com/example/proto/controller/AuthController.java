package com.example.proto.controller;

import com.example.proto.auth.AuthService;
import com.example.proto.dto.response.ApiDataResponse;
import com.example.proto.dto.request.LoginRequest;
import com.example.proto.dto.TokenDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiDataResponse<TokenDto> login(@Valid LoginRequest loginRequest) {
        TokenDto token = authService.login(loginRequest);

        return new ApiDataResponse<>(token);
    }
}
