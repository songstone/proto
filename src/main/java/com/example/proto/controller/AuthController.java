package com.example.proto.controller;

import com.example.proto.auth.JwtManager;
import com.example.proto.domain.Employee;
import com.example.proto.dto.ApiDataResponse;
import com.example.proto.dto.LoginRequest;
import com.example.proto.dto.TokenDto;
import com.example.proto.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final EmployeeService employeeService;

    @PostMapping("/login")
    public ApiDataResponse<TokenDto> login(@Valid LoginRequest loginRequest) {
        // TODO 로그인 서비스 분리, 토큰 처리 세분화
        Employee employeeInfo = employeeService.getEmployeeForLogin(loginRequest);
        String token = JwtManager.createToken(employeeInfo.getId(), employeeInfo.getEmail());
        return new ApiDataResponse<>(new TokenDto(token, token));
    }
}
