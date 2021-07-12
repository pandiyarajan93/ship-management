package com.hpc.shipmanagement.repository;

import com.hpc.shipmanagement.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRespository extends JpaRepository<Ship,Long> {
}
