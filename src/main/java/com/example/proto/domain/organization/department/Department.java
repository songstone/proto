package com.example.proto.domain.organization.department;

import com.example.proto.constant.YnStatus;
import com.example.proto.domain.organization.BaseEntity;
import com.example.proto.domain.organization.employee.Employee;
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
public class Department extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

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

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "CHAR(1)")
    private YnStatus isDeleted;

    public boolean isValid() {
        return YnStatus.N == this.isDeleted;
    }

    public void designateLeader(Employee employee) {
        this.leader = employee;
        employee.getManageDepartments().add(this);
    }

    public void registerParent(Department department) {
        this.parentDepartment = department;
        department.childDepartments.add(this);
    }
}
