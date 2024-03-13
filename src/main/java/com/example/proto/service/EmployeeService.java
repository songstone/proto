package com.example.proto.service;

import com.example.proto.domain.Employee;
import com.example.proto.domain.EmployeeRepository;
import com.example.proto.dto.LoginRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id) throws EntityNotFoundException {
        return employeeRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("해당하는 회원이 존재하지 않습니다.")
        );
    }

    public Employee getEmployeeForLogin(LoginRequest loginRequest) throws AccountNotFoundException {
        return employeeRepository.findByEmailAndPassword(loginRequest.email(), loginRequest.password()).orElseThrow(
            () -> new AccountNotFoundException("일치하는 계정정보가 존재하지 않습니다.")
        );
    }
}
