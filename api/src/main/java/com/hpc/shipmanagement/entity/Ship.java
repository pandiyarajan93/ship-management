package com.hpc.shipmanagement.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;



@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ship {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String code;
    @Column(unique = true)
    private String name;
    private double length;
    private double width;
}
