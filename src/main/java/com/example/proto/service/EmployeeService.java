package com.example.proto.service;

import com.example.proto.domain.*;
import com.example.proto.dto.EmployeeDto;
import com.example.proto.dto.RegisterDepartmentRequest;
import com.example.proto.exception.HubException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.proto.constant.ErrorCode.ENTITY_NOT_FOUND;

@RequiredArgsConstructor
@Transactional
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeDepartmentRepository employeeDepartmentRepository;

    @Transactional(readOnly = true)
    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .map(EmployeeDto::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public EmployeeDto getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
            () -> new HubException(ENTITY_NOT_FOUND, "해당하는 회원이 존재하지 않습니다.")
        );

        return EmployeeDto.fromEntity(employee);
    }

    public void registerDepartment(RegisterDepartmentRequest registerDepartmentRequest) {
        Employee employee = employeeRepository.findById(registerDepartmentRequest.en()).orElseThrow(
            () -> new HubException(ENTITY_NOT_FOUND, "해당하는 회원이 존재하지 않습니다.")
        );

        Department department = departmentRepository.findById(registerDepartmentRequest.dn()).orElseThrow(
            () -> new HubException(ENTITY_NOT_FOUND, "해당하는 부서가 존재하지 않습니다.")
        );

        EmployeeDepartment relation = EmployeeDepartment.builder()
            .employee(employee)
            .department(department)
            .build();

        employeeDepartmentRepository.save(relation);
    }
}
