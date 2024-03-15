package com.example.proto.exception;

import com.example.proto.constant.ErrorCode;
import com.example.proto.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiResponse> valid(MethodArgumentNotValidException e) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST;
        HttpStatus status = errorCode.getHttpStatus();

        return ResponseEntity.status(status).body(new ApiResponse(errorCode.getCode(), extractValidationDetailMessage(e)));
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse> hub(HubException e) {
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.getHttpStatus();

        return ResponseEntity.status(status).body(new ApiResponse(errorCode.getCode(), e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Object> exception(Exception e){
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        HttpStatus status = errorCode.getHttpStatus();

        return ResponseEntity.status(status).body(new ApiResponse(errorCode.getCode(), errorCode.getMessage()));
    }

    private String extractValidationDetailMessage(MethodArgumentNotValidException e) {
        return e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    }
}
