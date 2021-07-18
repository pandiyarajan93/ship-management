package com.hpc.shipmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiErrorResponse {

    private int errorCode;
    private String errorMessage;
}
