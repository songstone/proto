package com.example.proto.controller;

import com.example.proto.domain.organization.query_dto.EmployeeListDto;
import com.example.proto.dto.ApiDataResponse;
import com.example.proto.dto.PageInfo;
import com.example.proto.dto.organization.EmployeeDto;
import com.example.proto.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ApiDataResponse<PageInfo> getEmployees(Pageable pageable) {
        Page<EmployeeListDto> employees = employeeService.getEmployees(pageable);

        return new ApiDataResponse<>(PageInfo.of(employees.getContent(), employees.getTotalElements()));
    }

    @GetMapping("/{id}")
    public ApiDataResponse<EmployeeDto> getEmployee(@PathVariable(name = "id") Integer id) {
        EmployeeDto employee = employeeService.getEmployee(id);

        return new ApiDataResponse<>(employee);
    }
}
