package com.example.proto.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    BAD_REQUEST(4000, "BAD_REQUEST"),
    ENTITY_NOT_FOUND(4001, "ENTITY_NOT_FOUND"),

    INTERNAL_SERVER_ERROR(5000, "INTERVAL_SERVER_ERROR"),

    ;

    private final Integer code;
    private final String message;

    public HttpStatus httpStatus() {
        return isClientError()
            ? HttpStatus.BAD_REQUEST
            : HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private boolean isClientError() {
        return this.code/1000 == 4;
    }

}
