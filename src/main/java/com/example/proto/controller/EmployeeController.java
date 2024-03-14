package com.example.proto.controller;

import com.example.proto.domain.Employee;
import com.example.proto.dto.ApiDataResponse;
import com.example.proto.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/")
    public ApiDataResponse<List<Employee>> getEmployees() {
        List<Employee> employees = employeeService.getEmployees();

        return new ApiDataResponse<>(employees);
    }

    @GetMapping("/{id}")
    public ApiDataResponse<Employee> getEmployee(@PathVariable(name = "id") Long id) {
        Employee employee = employeeService.getEmployee(id);

        return new ApiDataResponse<>(employee);
    }
}