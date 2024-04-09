package com.example.proto.exception;

import com.example.proto.constant.ErrorCode;
import com.example.proto.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.proto.constant.ErrorCode.BAD_REQUEST;
import static com.example.proto.constant.ErrorCode.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiResponse> valid(MethodArgumentNotValidException e) {
        ObjectError detailError = extractValidationDetailError(e);
        String detailMessage = detailError.getDefaultMessage();

        log.error("[{}] {}", detailError.getObjectName(), detailMessage);

        ErrorCode errorCode = BAD_REQUEST;

        return ResponseEntity
            .status(errorCode.getHttpStatus())
            .body(new ApiResponse(errorCode.getCode(), detailMessage));
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse> hub(HubException e) {
        log.error("[{}] {}", e.getClass().getName() , e.getMessage());

        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity
            .status(errorCode.getHttpStatus())
            .body(new ApiResponse(errorCode.getCode(), e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse> exception(Exception e){
        log.error("[{}] {}", e.getClass().getName(), e.getMessage());

        ErrorCode errorCode = INTERNAL_SERVER_ERROR;

        return ResponseEntity
            .status(errorCode.getHttpStatus())
            .body(new ApiResponse(errorCode.getCode(), errorCode.name()));
    }

    private ObjectError extractValidationDetailError(MethodArgumentNotValidException e) {
        return e.getBindingResult().getAllErrors().get(0);
    }
}
