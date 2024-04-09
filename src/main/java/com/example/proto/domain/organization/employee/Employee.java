package com.example.proto.domain.organization.employee;

import com.example.proto.constant.NyStatus;
import com.example.proto.domain.organization.BaseEntity;
import com.example.proto.domain.organization.department.Department;
import com.example.proto.domain.organization.department.EmployeeDepartment;
import com.example.proto.domain.organization.position.Position;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.proto.constant.NyStatus.Y;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@DynamicInsert
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rank_no")
    private Position rank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_no")
    private Position position;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "INT")
    private NyStatus isActive;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "INT")
    private NyStatus isWithdraw;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "CHAR(1)")
    private NyStatus isDeleted;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EmployeeDepartment> employeeDepartments = new ArrayList<>();

    @OneToMany(mappedBy = "leader", fetch = FetchType.LAZY)
    private List<Department> manageDepartments = new ArrayList<>();


    public List<Department> getDepartments() {
        return this.employeeDepartments.stream()
            .map(EmployeeDepartment::getDepartment)
            .toList();
    }

    public boolean isInvalid() {
        return !isActive() || isWithdrawn() || isDeleted();
    }

    public boolean isActive() {
        return Y == this.isActive;
    }

    public boolean isWithdrawn() {
        return Y == this.isWithdraw;
    }

    public boolean isDeleted() {
        return Y == this.isDeleted;
    }
}
