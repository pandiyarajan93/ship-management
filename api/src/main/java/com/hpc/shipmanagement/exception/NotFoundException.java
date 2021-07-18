package com.hpc.shipmanagement.exception;

public class NotFoundException extends RuntimeException  {
    public NotFoundException(String message) {
        super(message);
    }
}
