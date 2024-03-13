package com.example.proto.controller;

import com.example.proto.auth.JwtManager;
import com.example.proto.domain.Employee;
import com.example.proto.dto.LoginRequest;
import com.example.proto.dto.StandardResponse;
import com.example.proto.dto.TokenDto;
import com.example.proto.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/common")
public class LoginController {

    private final EmployeeService employeeService;

    @PostMapping("/login")
    public StandardResponse<TokenDto> login(@Valid LoginRequest loginRequest) throws AccountNotFoundException {
        Employee employeeInfo = employeeService.getEmployeeForLogin(loginRequest);
        String token = JwtManager.createToken(employeeInfo.getId(), employeeInfo.getEmail());

        return new StandardResponse<>(new TokenDto(token));
    }
}
