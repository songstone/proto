package com.example.proto.domain.organization.employee;

import com.example.proto.constant.YnStatus;
import com.example.proto.domain.organization.BaseEntity;
import com.example.proto.domain.organization.department.Department;
import com.example.proto.domain.organization.department.EmployeeDepartment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private String email;
    private String password;
    private String name;
    private String phone;
    private Integer pay;
    private LocalDate applyDate;
    private Integer tempPay;

    private LocalDate employDate;

    private Integer rankNo;
    private Integer positionNo;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "INT")
    private YnStatus isActive;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "INT")
    private YnStatus isWithdraw;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EmployeeDepartment> employeeDepartments = new ArrayList<>();

    @OneToMany(mappedBy = "leader", fetch = FetchType.LAZY)
    private List<Department> manageDepartments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "CHAR(1)")
    private YnStatus isDeleted;

    public boolean isValid() {
        return YnStatus.N == this.isDeleted;
    }
}
