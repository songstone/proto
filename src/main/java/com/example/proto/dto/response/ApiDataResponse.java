package com.example.proto.dto.response;

import lombok.Getter;

@Getter
public class ApiDataResponse<T> extends ApiResponse {

    private final T data;

    public ApiDataResponse(T data) {
        super(2000, "SUCCESS");
        this.data = data;
    }

    public ApiDataResponse(T data, String message) {
        super(2000, message);
        this.data = data;
    }
}
