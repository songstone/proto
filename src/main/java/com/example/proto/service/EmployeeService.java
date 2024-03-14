package com.example.proto.service;

import com.example.proto.constant.ErrorCode;
import com.example.proto.domain.Employee;
import com.example.proto.domain.EmployeeRepository;
import com.example.proto.dto.LoginRequest;
import com.example.proto.exception.HubException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

import static com.example.proto.constant.ErrorCode.ENTITY_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow(
            () -> new HubException(ENTITY_NOT_FOUND, "해당하는 회원이 존재하지 않습니다.")
        );
    }

    public Employee getEmployeeForLogin(LoginRequest loginRequest) {
        return employeeRepository.findByEmailAndPassword(loginRequest.email(), loginRequest.password()).orElseThrow(
            () -> new HubException(ENTITY_NOT_FOUND, "일치하는 계정정보가 존재하지 않습니다.")
        );
    }
}
