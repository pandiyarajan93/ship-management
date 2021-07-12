package com.hpc.shipmanagement.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Ship {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "Ship id",name = "id",required = true)
    private long id;

    @ApiModelProperty(value = "Ship Name",name = "ship name",required = true)
    private String shipName;

    private double length;
    private double width;
    private String code;
}
