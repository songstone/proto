package com.example.proto.config.component;

import com.example.proto.domain.organization.employee.Employee;
import com.example.proto.domain.organization.employee.EmployeeRepository;
import com.example.proto.exception.HubException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.example.proto.constant.ErrorCode.INVALID_TOKEN;

@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtManager jwtManager;
    private final EmployeeRepository employeeRepository;

    public final String USER_ID_KEY = "empNo";

    @Override
    public boolean preHandle(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull Object handler
    ) throws Exception {
        String token = jwtManager.extractToken(request);
        validateTokenInfo(token);

        Integer empNo = Integer.parseInt(jwtManager.getSubject(token));
        validateEmployeeInfo(empNo);

        // TODO 유저 IDX 저장 방식 조정 필요
        request.setAttribute(USER_ID_KEY, empNo);

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private void validateTokenInfo(String token) {
        if(!StringUtils.hasText(token)) {
            throw new HubException(INVALID_TOKEN, "인증 토큰정보가 존재하지 않습니다.");
        }
        if (!jwtManager.verifyToken(token)) {
            throw new HubException(INVALID_TOKEN, "유효하지 않은 토큰 입니다.");
        }
    }

    private void validateEmployeeInfo(Integer empNo) {
        Employee employee = employeeRepository.findById(empNo).orElseThrow(
            () -> new HubException(INVALID_TOKEN, "인증정보가 존재하지 않습니다.")
        );
        if(employee.isInvalid()) {
            throw new HubException(INVALID_TOKEN, "인증정보가 유효하지 않습니다.");
        }
    }

    public Object getUserIdInfo(HttpServletRequest request) {
        return request.getAttribute(USER_ID_KEY);
    }
}
