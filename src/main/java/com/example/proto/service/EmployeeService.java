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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.proto.constant.ErrorCode.ENTITY_NOT_FOUND;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeDepartmentRepository employeeDepartmentRepository;

    public Page<EmployeeListDto> getEmployees(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);

        var employeeDepartmentMap = employeeDepartmentRepository.findFetchAllByEmployeeIn(employees.getContent())
            .stream()
            .collect(groupingBy(EmployeeDepartment::getEmployeeIdx,
                mapping(EmployeeDepartment::getDepartment, toList())));

        return employees.map(e ->
                EmployeeListDto.of(e, employeeDepartmentMap.getOrDefault(e.getIdx(), emptyList())));
    }

    public EmployeeDto getEmployee(Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
            () -> new HubException(ENTITY_NOT_FOUND, "해당하는 회원이 존재하지 않습니다.")
        );

        return EmployeeDto.of(employee);
    }
}
