package com.hpc.shipmanagement.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDto {

    @NotNull(message = "name should not be empty")
    private String name;

    @Pattern(message = "code format should be valid", regexp = "^[a-zA-z]{4}-[0-9]{4}-[a-zA-z]{1}[0-9]{1}$")
    private String code;

    private double length;

    private double width;
}
