package com.example.proto.domain.organization.query_dto;

import com.example.proto.domain.organization.department.Department;
import com.example.proto.domain.organization.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public record EmployeeListDto(
    Integer no,
    Integer isActive,
    String empName,
    Integer rankNo,
    Integer positionNo,
    String email,
    String phone,
    List<DepartmentDto> deptList
) {
    public static EmployeeListDto of(Employee employee, List<Department> departments) {
        return new EmployeeListDto(
            employee.getIdx(),
            employee.getIsActive().ordinal(),
            employee.getName(),
            employee.getRankNo(),
            employee.getPositionNo(),
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
