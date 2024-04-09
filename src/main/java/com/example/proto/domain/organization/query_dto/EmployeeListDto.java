package com.example.proto.domain.organization.query_dto;

import com.example.proto.domain.organization.department.Department;
import com.example.proto.domain.organization.employee.Employee;
import com.example.proto.domain.organization.position.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

public record EmployeeListDto(
    Integer no,
    Integer isActive,
    String empName,
    String rankName,
    String positionName,
    String email,
    String phone,
    List<DepartmentDto> deptList
) {
    public static EmployeeListDto of(Employee employee, List<Department> departments) {
        Optional<String> rankNameOptional = Optional.ofNullable(employee.getRank())
            .map(Position::getName);
        Optional<String> positionNameOptional = Optional.ofNullable(employee.getPosition())
            .map(Position::getName);

        return new EmployeeListDto(
            employee.getIdx(),
            employee.getIsActive().ordinal(),
            employee.getName(),
            rankNameOptional.orElse("미정"),
            positionNameOptional.orElse("미정"),
            employee.getEmail(),
            employee.getPhone(),
            departments.stream().map(DepartmentDto::of).toList()
        );
    }

    @Getter
    @AllArgsConstructor
    public static class DepartmentDto {
        private Integer no;
        private String name;
        private Integer leaderNo;
        private Integer parentNo;
        private String parentName;

        public static DepartmentDto of(Department department) {
            Department parentDepartment = department.getParentDepartment();
            Integer parentNo = (parentDepartment != null) ? parentDepartment.getIdx() : null;
            String parentName = (parentDepartment != null) ? parentDepartment.getName() : null;

            Employee leader = department.getLeader();
            Integer leaderNo = leader != null ? leader.getIdx() : null;

            return new DepartmentDto(
                department.getIdx(),
                department.getName(),
                leaderNo,
                parentNo,
                parentName
            );
        }
    }
}
