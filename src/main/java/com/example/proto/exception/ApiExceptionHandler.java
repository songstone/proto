package com.example.proto.exception;

import com.example.proto.constant.ErrorCode;
import com.example.proto.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiResponse> hub(HubException e) {
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.httpStatus();

        return ResponseEntity.status(status).body(new ApiResponse(errorCode.getCode(), e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Object> exception(Exception e){
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(status).body(new ApiResponse(errorCode.getCode(), errorCode.getMessage()));
    }
}
