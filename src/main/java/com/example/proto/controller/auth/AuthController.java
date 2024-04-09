package com.example.proto.controller.auth;

import com.example.proto.service.auth.AuthService;
import com.example.proto.dto.organization.request.EmployeeRequest;
import com.example.proto.dto.ApiDataResponse;
import com.example.proto.dto.auth.TokenDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ApiDataResponse<TokenDto> formToken(@Valid EmployeeRequest.Token tokenRequest) {
        TokenDto token = authService.token(tokenRequest);

        return new ApiDataResponse<>(token);
    }

    @PostMapping(value = "/token")
    public ApiDataResponse<TokenDto> jsonToken(@RequestBody @Valid EmployeeRequest.Token tokenRequest) {
        TokenDto token = authService.token(tokenRequest);

        return new ApiDataResponse<>(token);
    }
}
