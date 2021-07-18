package com.hpc.shipmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CommonException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> handleNotFound(Exception e) {
        return buildResponseEntity(new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgument(Exception e) {
        return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(FunctionalException.class)
    public ResponseEntity<ApiErrorResponse> handleFunctionalException(FunctionalException e) {
        return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getErrorMessage()));
    }


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleSqlConstraintViolation(Exception e) {
        return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), "Ship code / name already exits"));
    }

    private ResponseEntity<ApiErrorResponse> buildResponseEntity(ApiErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getErrorCode()));
    }
}
