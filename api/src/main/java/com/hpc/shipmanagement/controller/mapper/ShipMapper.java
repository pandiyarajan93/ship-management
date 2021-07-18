package com.hpc.shipmanagement.controller.mapper;

import com.hpc.shipmanagement.entity.Ship;
import com.hpc.shipmanagement.model.RequestDto;
import com.hpc.shipmanagement.model.ResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface ShipMapper {

    ShipMapper mapper = getMapper(ShipMapper.class);

    List<ResponseDto> map(List<Ship> ships);

    Ship map(RequestDto dto);

    ResponseDto map(Ship ship);

}
