package com.hpc.shipmanagement.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hpc.shipmanagement.controller.mapper.ShipMapper;
import com.hpc.shipmanagement.entity.Ship;
import com.hpc.shipmanagement.exception.NotFoundException;
import com.hpc.shipmanagement.model.RequestDto;
import com.hpc.shipmanagement.service.ShipService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ShipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShipService service;

    @Autowired
    private ObjectMapper objectMapper;

    private static final ShipMapper mapper = ShipMapper.mapper;

    private static final String ship1 = "ship1";
    private static final String code1 = "AAAA-1111-A1";
    private static final String ship2 = "ship2";
    private static final String code2 = "BBBB-2222-B1";


    @Test
    public void should_create_ship() throws Exception {
        RequestDto dto = RequestDto.builder()
                .name(ship1)
                .code(code1)
                .length(10.2d)
                .width(34)
                .build();

        mockMvc.perform(post("/ship/save")
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_fetch_all_ship() throws Exception {
        List<Ship> ship = mockShipData();

        mockMvc.perform(get("/ship/fetch")
                .content(objectMapper.writeValueAsString(ship))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_fetch_ship_by_id() throws Exception {
        Ship ship = Ship.builder()
                .name("ship1")
                .code("xxxx-0000-x0")
                .length(12)
                .width(12)
                .build();

        when(service.findById(1L)).thenReturn(ship);

        mockMvc.perform(get("/ship/findById/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void should_update_existing_ship() throws Exception {
        RequestDto dto = RequestDto.builder()
                .name("ship1")
                .code("AAAA-1111-A1")
                .length(12.3)
                .width(23d)
                .build();
        Ship ship = mapper.map(dto);
        ship.setId(1);

        when(service.findById(1L)).thenReturn(ship);

        mockMvc.perform(put("/ship/update/{id}", 1)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_throw_error_when_id_inValid() throws Exception {
        RequestDto dto = RequestDto.builder()
                .name("ship1")
                .code("AAAA-1111-A1")
                .length(12.3)
                .width(23)
                .build();

        when(service.update(dto, 1L)).thenThrow(new NotFoundException("Not found"));

        mockMvc.perform(put("/ship/update/{id}", 1)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_delete_all_ships() throws Exception {

        RequestDto dto = RequestDto.builder().build();

        mockMvc.perform(delete("/ship/delete/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void should_throw_error_when_id_notFound() throws Exception {

        doThrow(new NotFoundException("ship id not found"))
                .when(service).delete(1);

        mockMvc.perform(delete("/ship/delete/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private List<Ship> mockShipData() {
        Ship ship1 = Ship.builder()
                .name("ship1")
                .code("AAAA-1111-A1")
                .length(12d)
                .width(2.3)
                .build();
        Ship ship2 = Ship.builder()
                .name("ship2")
                .code("BBBB-1111-B1")
                .length(14d)
                .width(2.3)
                .build();

        return Arrays.asList(ship1, ship2);
    }
}
