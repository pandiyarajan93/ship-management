package com.hpc.shipmanagement.service;

import com.hpc.shipmanagement.entity.Ship;
import com.hpc.shipmanagement.exception.NotFoundException;
import com.hpc.shipmanagement.model.RequestDto;
import com.hpc.shipmanagement.repository.ShipRespository;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ShipServiceImpl implements  ShipService{

    @Autowired
    ShipRespository shipRespository;

    @Override
    public Ship save(Ship ship) {
        return shipRespository.save(ship);
    }

    @Override
    public List<Ship> find() {
        return shipRespository.findAll();
    }

    @Override
    public Ship update(Ship ship) {
        return shipRespository.save(ship);
    }

    @Override
    public void delete(long id) {
        shipRespository.deleteById(id);
    }

    @Override
    public Ship findById(Long id) throws NotFoundException {
        return shipRespository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Ship id {id} is not available",id)));
    }

}
