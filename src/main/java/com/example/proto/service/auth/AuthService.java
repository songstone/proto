package com.example.proto.service.auth;

import com.example.proto.config.component.BcryptPasswordEncoder;
import com.example.proto.config.component.JwtManager;
import com.example.proto.domain.organization.employee.Employee;
import com.example.proto.domain.organization.employee.EmployeeRepository;
import com.example.proto.dto.auth.TokenDto;
import com.example.proto.dto.organization.request.EmployeeRequest;
import com.example.proto.exception.HubException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.proto.constant.ErrorCode.ENTITY_NOT_FOUND;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AuthService {

    private final JwtManager jwtManager;
    private final EmployeeRepository employeeRepository;

    public TokenDto token(EmployeeRequest.Token tokenRequest) {
        Employee employee = employeeRepository.findByEmail(tokenRequest.email())
            .orElseThrow(() -> new HubException(ENTITY_NOT_FOUND, "일치하는 계정정보가 존재하지 않습니다.")
        );
        if(!BcryptPasswordEncoder.verify(tokenRequest.password(), employee.getPassword())) {
            throw new HubException(ENTITY_NOT_FOUND, "일치하는 계정정보가 존재하지 않습니다.");
        }
        if(employee.isInvalid()) {
            throw new HubException(ENTITY_NOT_FOUND, "유효한 계정이 아닙니다. 관리자에게 문의하십시오.");
        }

        return new TokenDto(jwtManager.createToken(employee.getIdx()));
    }
}
