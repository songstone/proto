package com.example.proto.dto;

import lombok.Getter;

@Getter
public class ApiDataResponse<T> extends ApiResponse {

    private final T data;

    public ApiDataResponse(T data) {
        super(20000, "SUCCESS");
        this.data = data;
    }

    public ApiDataResponse(T data, String message) {
        super(20000, message);
        this.data = data;
    }
}
