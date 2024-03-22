package com.example.proto.domain.organization.department;

import com.example.proto.constant.YnStatus;
import com.example.proto.domain.organization.BaseEntity;
import com.example.proto.domain.organization.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

import static com.example.proto.constant.YnStatus.N;

@Getter
@Builder
@NoArgsConstructor
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
    private YnStatus isDeleted;

    public boolean isValid() {
        return N == this.isDeleted;
    }

    public void designateLeader(Employee employee) {
        this.leader = employee;
        employee.getManageDepartments().add(this);
    }

    public void registerParent(Department department) {
        if(department == null) return;
        this.parentDepartment = department;
        department.childDepartments.add(this);
    }
}
