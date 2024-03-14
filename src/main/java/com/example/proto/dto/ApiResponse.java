package com.example.proto.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiResponse {
    private final Integer code;
    private final String message;
}
