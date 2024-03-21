package com.example.proto.domain.organization.department;

import com.example.proto.domain.organization.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeDepartmentRepository extends JpaRepository<EmployeeDepartment, Long> {

    @Query("SELECT ed FROM EmployeeDepartment ed JOIN FETCH ed.department d JOIN FETCH ed.employee e")
    List<EmployeeDepartment> findFetchAllByEmployeeIn(List<Employee> employees);
}
