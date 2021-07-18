package com.hpc.shipmanagement.service;


import com.hpc.shipmanagement.entity.Ship;
import com.hpc.shipmanagement.exception.NotFoundException;
import com.hpc.shipmanagement.model.RequestDto;

import java.util.List;


public interface ShipService {

    Ship save(Ship ship);

    List<Ship> find();

    Ship update(RequestDto ship, Long id);

    void delete(long id);

    Ship findById(Long id) throws NotFoundException;

    void validate(RequestDto dto);
}
