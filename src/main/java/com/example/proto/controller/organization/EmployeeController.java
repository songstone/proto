package com.example.proto.controller.organization;

import com.example.proto.domain.organization.query_dto.EmployeeListDto;
import com.example.proto.dto.ApiDataResponse;
import com.example.proto.dto.PageInfo;
import com.example.proto.dto.organization.EmployeeDto;
import com.example.proto.dto.organization.request.EmployeeRequest;
import com.example.proto.service.organization.EmployeeService;
import jakarta.validation.Valid;
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
    public ApiDataResponse<PageInfo> getEmployees(@Valid EmployeeRequest.List listRequest, Pageable pageable) {
        Page<EmployeeListDto> employees = employeeService.getEmployees(listRequest, pageable);

        return new ApiDataResponse<>(PageInfo.of(employees.getContent(), employees.getTotalElements()));
    }

    @GetMapping("/{id}")
    public ApiDataResponse<EmployeeDto> getEmployee(@PathVariable(name = "id") Integer id) {
        EmployeeDto employee = employeeService.getEmployee(id);

        return new ApiDataResponse<>(employee);
    }
}
