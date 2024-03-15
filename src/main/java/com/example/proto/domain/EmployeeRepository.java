package com.example.proto.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.isDeleted = 'N' AND e.email = :email AND e.password = :password")
    Optional<Employee> findUserForLogin(String email, String password);
}
