package com.hpc.shipmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDto {

    private Long id;

    @NotNull(message="code should not be empty")
    private String code;

    @NotNull(message = "name should not be empty")
    private String name;

    private double length;

    private double width;
}
