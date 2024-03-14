package com.example.proto.service;

import com.example.proto.domain.Employee;
import com.example.proto.domain.EmployeeRepository;
import com.example.proto.dto.EmployeeDto;
import com.example.proto.exception.HubException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.proto.constant.ErrorCode.ENTITY_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .map(EmployeeDto::fromEntity)
                .toList();
    }

    public EmployeeDto getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new HubException(ENTITY_NOT_FOUND, "해당하는 회원이 존재하지 않습니다.")
        );

        return EmployeeDto.fromEntity(employee);
    }
}
