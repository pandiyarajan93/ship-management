package com.hpc.shipmanagement.service;

import com.hpc.shipmanagement.entity.Ship;
import com.hpc.shipmanagement.exception.FunctionalException;
import com.hpc.shipmanagement.exception.NotFoundException;
import com.hpc.shipmanagement.model.RequestDto;
import com.hpc.shipmanagement.repository.ShipRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipServiceImpl implements ShipService {

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
    public Ship update(RequestDto dto, Long id) {
        Ship ship = findById(id);

        ship.setCode(dto.getCode());
        ship.setName(dto.getName());
        ship.setLength(dto.getLength());
        ship.setWidth(dto.getWidth());

        return shipRespository.save(ship);
    }

    @Override
    public void delete(long id)  {
        Ship ship = findById(id);
            shipRespository.delete(ship);
    }

    @Override
    public Ship findById(Long id) throws NotFoundException {
        return shipRespository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Ship id %d is not found", id)));
    }

    public void validate(RequestDto dto) {

        String regexp = "^[a-zA-z]{4}-[0-9]{4}-[a-zA-z]{1}[0-9]{1}$";
        if (!dto.getCode().matches(regexp)) {
            throw new FunctionalException("Ship Code Invalid");
        }
        if (dto.getLength() <= 0 || dto.getWidth() <= 0) {
            throw new FunctionalException("Length/Width Should be More than Zero");
        }
    }
}
