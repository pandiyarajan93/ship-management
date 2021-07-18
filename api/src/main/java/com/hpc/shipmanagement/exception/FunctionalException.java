package com.hpc.shipmanagement.exception;


import lombok.Getter;

@Getter
public class FunctionalException extends RuntimeException{

    private String errorMessage;

    public FunctionalException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
