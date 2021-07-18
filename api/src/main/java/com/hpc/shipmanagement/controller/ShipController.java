package com.hpc.shipmanagement.controller;

import com.hpc.shipmanagement.entity.Ship;
import com.hpc.shipmanagement.exception.NotFoundException;
import com.hpc.shipmanagement.model.RequestDto;
import com.hpc.shipmanagement.model.ResponseDto;
import com.hpc.shipmanagement.service.ShipService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.hpc.shipmanagement.controller.mapper.ShipMapper.mapper;

/**
 * controller for ship management operation
 */

@RestController
@RequestMapping("/ship/")
@ApiOperation(value = "/ship/management", tags = "ShipManagement Controller")
@Validated
@RequiredArgsConstructor
public class ShipController {


    private final ShipService shipService;

    /**
     * Create new ship
     *
     * @param dto objects
     * @return ResponseDto
     */
    @PostMapping("save")
    @ApiOperation(value = "Save Ship Information", response = ResponseDto.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto> saveShipInfo(@RequestBody @Valid RequestDto dto) {
        shipService.validate(dto);
        Ship ship = shipService.save(mapper.map(dto));
        return new ResponseEntity<>(mapper.map(ship), HttpStatus.CREATED);
    }

    /**
     * fetch all the ship information
     *
     * @return list of dto
     */
    @GetMapping("fetch")
    @ApiOperation(value = "Fetch Ship Information")
    @ApiResponses(value = {@ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "NOT FOUND")})
    public ResponseEntity<List<ResponseDto>> getAllShip() {
        return ResponseEntity.ok().body(mapper.map(shipService.find()));
    }

    /**
     * update existing ship information
     *
     * @param dto objects
     * @return Ship
     */
    @PutMapping("update/{id}")
    @ApiOperation(value = "Update Ship Information", response = Ship.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "NOT FOUND")})
    public ResponseEntity<ResponseDto> updateShipInfo(@RequestBody RequestDto dto, @PathVariable Long id) {
        shipService.validate(dto);

        Ship updateShip = shipService.update(dto, id);
        return new ResponseEntity<>(mapper.map(updateShip), HttpStatus.OK);
    }

    /**
     * delete ship information
     *
     * @param id the id of the ship
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "Delete Ship Information")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "SUCCESS"),
            @ApiResponse(code = 404, message = "NOT FOUND")})
    public void deleteShipInfo(@PathVariable long id) {
        shipService.delete(id);
    }

    /**
     * @param id the ship id
     * @return dto
     */
    @GetMapping("findById/{id}")
    @ApiOperation(value = "Find Ship Information By Id", response = ResponseDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 404, message = "NOT FOUND")})
    public ResponseEntity<ResponseDto> findById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok().body(mapper.map(shipService.findById(id)));
    }
}
