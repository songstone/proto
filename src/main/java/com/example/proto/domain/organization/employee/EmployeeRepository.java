package com.example.proto.domain.organization.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>, EmployeeRepositoryQuery {

    Optional<Employee> findByEmail(String email);
}
