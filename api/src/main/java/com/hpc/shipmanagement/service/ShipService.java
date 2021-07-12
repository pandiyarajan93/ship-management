package com.hpc.shipmanagement.service;


import com.hpc.shipmanagement.entity.Ship;
import com.hpc.shipmanagement.exception.NotFoundException;
import com.hpc.shipmanagement.model.RequestDto;
import com.hpc.shipmanagement.repository.ShipRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public interface ShipService {

     Ship save(Ship ship);

     List<Ship> find();

     Ship update(Ship ship);

     void delete(long id);

     Ship findById(Long id) throws NotFoundException;
}
