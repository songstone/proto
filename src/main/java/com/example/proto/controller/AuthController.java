package com.example.proto.controller;

import com.example.proto.auth.AuthService;
import com.example.proto.dto.response.ApiDataResponse;
import com.example.proto.dto.request.LoginRequest;
import com.example.proto.dto.TokenDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ApiDataResponse<TokenDto> formLogin(@Valid LoginRequest loginRequest) {
        TokenDto token = authService.login(loginRequest);

        return new ApiDataResponse<>(token);
    }

    @PostMapping(value = "/login")
    public ApiDataResponse<TokenDto> jsonLogin(@RequestBody @Valid LoginRequest loginRequest) {
        TokenDto token = authService.login(loginRequest);

        return new ApiDataResponse<>(token);
    }
}
