package com.example.proto.domain.organization.department;

import com.example.proto.domain.organization.employee.Employee;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employee_department")
public class EmployeeDepartment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_no")
    private Department department;

    public Integer getEmployeeIdx() {
        return employee.getIdx();
    }

    public Integer getDepartmentIdx() {
        return department.getIdx();
    }
}
