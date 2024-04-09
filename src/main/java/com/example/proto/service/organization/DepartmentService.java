package com.example.proto.service.organization;

import com.example.proto.domain.organization.department.Department;
import com.example.proto.domain.organization.department.DepartmentRepository;
import com.example.proto.domain.organization.employee.Employee;
import com.example.proto.domain.organization.employee.EmployeeRepository;
import com.example.proto.dto.organization.request.DepartmentRequest;
import com.example.proto.exception.HubException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.proto.constant.ErrorCode.ENTITY_NOT_FOUND;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public Integer create(DepartmentRequest.create createRequest) {
        Department department = Department.builder().name(createRequest.name()).build();

        Integer parentNo = createRequest.parentNo();
        if(parentNo != null) {
            Department parent = departmentRepository.findById(parentNo)
                .orElseThrow(
                    () -> new HubException(ENTITY_NOT_FOUND, "해당하는 상위 부서가 존재하지 않습니다.")
                );
            department.registerParent(parent);
        }

        return departmentRepository.save(department).getIdx();
    }

    @Transactional
    public Integer setLeader(DepartmentRequest.leader leaderRequest) {
        Department department = departmentRepository.findById(leaderRequest.departmentNo()).orElseThrow(
            () -> new HubException(ENTITY_NOT_FOUND, "해당하는 부서가 존재하지 않습니다.")
        );

        Employee employee = employeeRepository.findById(leaderRequest.employeeNo()).orElseThrow(
            () -> new HubException(ENTITY_NOT_FOUND, "해당하는 직원이 존재하지 않습니다.")
        );

        department.designateLeader(employee);

        return department.getIdx();
    }
}
