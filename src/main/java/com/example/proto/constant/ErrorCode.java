package com.example.proto.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    BAD_REQUEST(40000, HttpStatus.BAD_REQUEST, "BAD_REQUEST"),
    ENTITY_NOT_FOUND(40001, HttpStatus.BAD_REQUEST, "ENTITY_NOT_FOUND"),

    INVALID_TOKEN(40100, HttpStatus.UNAUTHORIZED, "INVALID_TOKEN"),

    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR,"INTERVAL_SERVER_ERROR"),

    ;

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;

}
