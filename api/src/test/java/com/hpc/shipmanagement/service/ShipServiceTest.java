package com.hpc.shipmanagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hpc.shipmanagement.entity.Ship;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ShipServiceTest {

    @Mock
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeAll
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void should_create_new_ship() throws Exception {
        Ship ship  = mockData();
        mockMvc.perform(post("/ship/save")
                .content(objectMapper.writeValueAsString(ship))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    
    public Ship mockData(){
        Ship ship = new Ship();
        ship.setId(1L);
        ship.setShipName("ship1");
        ship.setCode(String.format("AAAA-1111-A1"));
        ship.setLength(12.4);
        ship.setWidth(10.4);
        return ship;
    }
}
