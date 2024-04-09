package com.example.proto.exception;

import com.example.proto.constant.ErrorCode;
import lombok.Getter;

@Getter
public class HubException extends RuntimeException {

    private final ErrorCode errorCode;

    public HubException(ErrorCode errorCode) {
        super(errorCode.name());
        this.errorCode = errorCode;
    }

    public HubException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
