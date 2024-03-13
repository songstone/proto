package com.example.proto.dto;

import lombok.Data;

@Data
public class StandardResponse<T> {
    private int code = 2000;
    private T data;

    public StandardResponse(T data) {
        this.data = data;
    }
}
