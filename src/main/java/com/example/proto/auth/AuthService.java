package com.example.proto.auth;

import com.example.proto.domain.Employee;
import com.example.proto.domain.EmployeeRepository;
import com.example.proto.dto.request.LoginRequest;
import com.example.proto.dto.TokenDto;
import com.example.proto.exception.HubException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.proto.constant.ErrorCode.ENTITY_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final JwtManager jwtManager;
    private final EmployeeRepository employeeRepository;

    public TokenDto login(LoginRequest loginRequest) {
        Employee employee = employeeRepository.findUserForLogin(loginRequest.email(), loginRequest.password())
            .orElseThrow(() -> new HubException(ENTITY_NOT_FOUND, "일치하는 계정정보가 존재하지 않습니다.")
        );

        return new TokenDto(jwtManager.createToken(employee.getId()));
    }

}
