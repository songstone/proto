package com.example.proto.controller.organization;

import com.example.proto.dto.ApiDataResponse;

import com.example.proto.dto.organization.request.DepartmentRequest;
import com.example.proto.service.organization.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ApiDataResponse<Integer> getEmployee(DepartmentRequest.create createRequest) {

        return new ApiDataResponse<>(departmentService.create(createRequest));
    }

    @PostMapping(value = "/leader", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ApiDataResponse<Integer> getEmployee(DepartmentRequest.leader leaderRequest) {

        return new ApiDataResponse<>(departmentService.setLeader(leaderRequest));
    }
}
