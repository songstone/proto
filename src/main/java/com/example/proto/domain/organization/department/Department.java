package com.example.proto.domain.organization.department;

import com.example.proto.constant.NyStatus;
import com.example.proto.domain.organization.BaseEntity;
import com.example.proto.domain.organization.employee.Employee;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

import static com.example.proto.constant.NyStatus.Y;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
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
    private NyStatus isDeleted;

    public boolean isInvalid() {
        return isDeleted();
    }

    public boolean isDeleted() {
        return Y == this.isDeleted;
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
