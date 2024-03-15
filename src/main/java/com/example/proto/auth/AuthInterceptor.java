package com.example.proto.auth;

import com.example.proto.constant.ErrorCode;
import com.example.proto.domain.Employee;
import com.example.proto.domain.EmployeeRepository;
import com.example.proto.exception.HubException;
import com.example.proto.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtManager jwtManager;
    private final EmployeeRepository employeeRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = jwtManager.extractToken(request);
        validateTokenInfo(token);

        Long empNo = Long.parseLong(jwtManager.getSubject(token));
        request.setAttribute("employee_no", empNo);

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private void validateTokenInfo(String token) {
        if(!StringUtils.hasText(token)) {
            throw new HubException(ErrorCode.INVALID_TOKEN, "인증 토큰정보가 존재하지 않습니다.");
        }
        if (!jwtManager.verifyToken(token)) {
            throw new HubException(ErrorCode.INVALID_TOKEN, "유효하지 않은 토큰 입니다.");
        }

        Long empNo = Long.parseLong(jwtManager.getSubject(token));

        Employee employee = employeeRepository.findById(empNo).orElseThrow(
            () -> new HubException(ErrorCode.INVALID_TOKEN, "존재하지 않는 인증 정보입니다.")
        );
        if(!employee.isValid()) {
            throw new HubException(ErrorCode.INVALID_TOKEN, "존재하지 않는 인증 정보입니다.");
        }
    }
}
