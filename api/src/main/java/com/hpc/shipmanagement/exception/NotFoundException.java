package com.hpc.shipmanagement.exception;

import com.hpc.shipmanagement.service.ShipService;

import java.util.function.Supplier;

public class NotFoundException extends Exception  {
    public NotFoundException(String message) {
        super(message);
    }
}
