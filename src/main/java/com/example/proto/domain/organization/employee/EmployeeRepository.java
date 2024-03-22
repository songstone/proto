package com.example.proto.domain.organization.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmail(String email);

    @Query(
        "SELECT e FROM Employee e " +
            "LEFT JOIN fetch e.employeeDepartments ed " +
            "LEFT JOIN fetch ed.department d"
    )
    List<Employee> findAllFetch();
}
