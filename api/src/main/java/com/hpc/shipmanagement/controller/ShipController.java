package com.hpc.shipmanagement.controller;

import com.hpc.shipmanagement.entity.Ship;
import com.hpc.shipmanagement.exception.NotFoundException;
import com.hpc.shipmanagement.mapper.ShipMapper;
import com.hpc.shipmanagement.model.RequestDto;
import com.hpc.shipmanagement.model.ResponseDto;
import com.hpc.shipmanagement.service.ShipService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.mappers.ModelMapper;

import java.awt.image.ImageProducer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static com.hpc.shipmanagement.mapper.ShipMapper.mapper;

@RestController
@RequestMapping("/ship/")
@ApiOperation(value = "/ship/management", tags = "ShipManagement Controller")
public class ShipController{

    @Autowired
    ShipService shipService;

    /**
     * Create new ship
     * @param dto the ship model object
     * @return string
     */
    @PostMapping("save")
    @ApiOperation(value = "Save Ship Information", response = Ship.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto> saveShipInfo(@RequestBody @Validated RequestDto dto) throws URISyntaxException {
        Ship ship = shipService.save(mapper.map(dto));
        return ResponseEntity.created(new URI("")).body(mapper.map(ship));
    }

    /**
     * fetch all the ship information
     * @return list of ships
     */
    @GetMapping("fetch")
    @ApiOperation(value = "Fetch Ship Information", response = Iterable.class)
    @ApiResponses(value = {@ApiResponse(code = 200,message = "SUCCESS"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED"),
            @ApiResponse(code = 403, message = "FORBIDDEN"),
            @ApiResponse(code =404, message = "NOT FOUND")})
    public ResponseEntity<List<ResponseDto>> findShipInfo(){
        return ResponseEntity.ok().body(mapper.map(shipService.find()));
    }

    /**
     * update existing ship information
     * @param ship the Ship model objects
     * @return string
     */
    @PutMapping("update")
    @ApiOperation(value = "Update Ship Information", response = Ship.class)
    @ApiResponses(value = {@ApiResponse(code = 200,message = "SUCCESS"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED"),
            @ApiResponse(code = 403, message = "FORBIDDEN"),
            @ApiResponse(code =404, message = "NOT FOUND")})
    public ResponseEntity<Ship> updateShipInfo(@RequestBody Ship ship){
        return ResponseEntity.ok().body(shipService.update(ship));
    }

    /**
     * delete ship information
     * @param id the id of the ship
     * @return string
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "Delete Ship Information", response = String.class)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {@ApiResponse(code = 200,message = "SUCCESS"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED"),
            @ApiResponse(code = 403, message = "FORBIDDEN"),
            @ApiResponse(code =404, message = "NOT FOUND")})
    public void deleteShipInfo(@PathVariable long id){
        shipService.delete(id);
    }

    /**
     *
     * @param id the ship id
     * @return the Ship model object
     * @throws NotFoundException
     */
    @GetMapping("find/{id}")
    @ApiOperation(value = "Find Ship Information By Id", response = Ship.class)
    @ApiResponses(value = {@ApiResponse(code = 200,message = "SUCCESS"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED"),
            @ApiResponse(code = 403, message = "FORBIDDEN"),
            @ApiResponse(code =404, message = "NOT FOUND"),
            @ApiResponse(code = 500,message = "INTERNAL SERVER ERROR", response = NotFoundException.class)})
    public ResponseEntity<ResponseDto> findById(@RequestParam Long id) throws NotFoundException {
        return ResponseEntity.ok().body(mapper.map(shipService.findById(id)));
    }
}
