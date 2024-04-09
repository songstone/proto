package com.example.proto.domain.organization.employee;

import com.example.proto.dto.organization.request.EmployeeRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeRepositoryQuery {

    Page<Employee> getEmployeesSearch(EmployeeRequest.List request, Pageable pageable);
}
