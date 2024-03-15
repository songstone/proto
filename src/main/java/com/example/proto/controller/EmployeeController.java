package com.example.proto.controller;

import com.example.proto.dto.RegisterDepartmentRequest;
import com.example.proto.dto.response.ApiDataResponse;
import com.example.proto.dto.EmployeeDto;
import com.example.proto.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ApiDataResponse<List<EmployeeDto>> getEmployees() {
        List<EmployeeDto> employees = employeeService.getEmployees();

        return new ApiDataResponse<>(employees);
    }

    @GetMapping("/{id}")
    public ApiDataResponse<EmployeeDto> getEmployee(@PathVariable(name = "id") Long id) {
        EmployeeDto employee = employeeService.getEmployee(id);

        return new ApiDataResponse<>(employee);
    }

    @PostMapping("/department")
    public ApiDataResponse<Integer> registerDepartment(@Valid RegisterDepartmentRequest registerDepartmentRequest) {
        employeeService.registerDepartment(registerDepartmentRequest);

        return new ApiDataResponse<>(1);
    }
}
