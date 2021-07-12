package com.hpc.shipmanagement.mapper;

import com.hpc.shipmanagement.entity.Ship;
import com.hpc.shipmanagement.model.RequestDto;
import com.hpc.shipmanagement.model.ResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ShipMapper {

    ShipMapper mapper = Mappers.getMapper(ShipMapper.class);

    List<ResponseDto> map(List<Ship> ships);

    Ship map(RequestDto dto);

    ResponseDto map(Ship ship);
}
