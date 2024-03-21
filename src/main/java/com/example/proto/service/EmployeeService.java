package com.example.proto.service;

import com.example.proto.domain.organization.department.DepartmentRepository;
import com.example.proto.domain.organization.department.EmployeeDepartment;
import com.example.proto.domain.organization.department.EmployeeDepartmentRepository;
import com.example.proto.domain.organization.employee.Employee;
import com.example.proto.domain.organization.employee.EmployeeRepository;
import com.example.proto.domain.organization.query_dto.EmployeeListDto;
import com.example.proto.dto.organization.EmployeeDto;
import com.example.proto.exception.HubException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static com.example.proto.constant.ErrorCode.ENTITY_NOT_FOUND;
import static java.util.Collections.*;
import static java.util.stream.Collectors.groupingBy;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeDepartmentRepository employeeDepartmentRepository;

    public List<EmployeeListDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        var employeeDepartmentMap = employeeDepartmentRepository.findFetchAllByEmployeeIn(employees).stream()
            .collect(groupingBy(ed -> ed.getEmployee().getIdx()));

        return employees.stream()
            .map(e -> EmployeeListDto.of(
                e, employeeDepartmentMap.getOrDefault(e.getIdx(), emptyList())
                    .stream()
                    .map(EmployeeDepartment::getDepartment)
                    .toList()))
            .toList();
    }

    public EmployeeDto getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
            () -> new HubException(ENTITY_NOT_FOUND, "해당하는 회원이 존재하지 않습니다.")
        );

        return EmployeeDto.of(employee);
    }
}
