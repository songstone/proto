package com.example.proto.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "department")
public class Department {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_no")
    private Department parentDepartment;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_no")
    private Employee leader;

    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
    private List<EmployeeDepartment> employeeDepartments = new ArrayList<>();

    @OneToMany(mappedBy = "parentDepartment", fetch = FetchType.LAZY)
    private List<Department> childDepartments = new ArrayList<>();

    public void designateLeader(Employee employee) {
        this.leader = employee;
        employee.getManageDepartments().add(this);
    }

    public void registerParent(Department department) {
        this.parentDepartment = department;
        department.childDepartments.add(this);
    }

    // TODO 분리
    @Column(length = 1)
    private String isDeleted;

    @CreatedDate
    private LocalDateTime createdAt;

    public boolean isValid() {
        return "N".equals(this.isDeleted);
    }
}
