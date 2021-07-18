package com.hpc.shipmanagement.integration;

import com.hpc.shipmanagement.CommonUtilIntegrationTest;
import com.hpc.shipmanagement.entity.Ship;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReadIntegrationTest extends CommonUtilIntegrationTest {

    String path = contextPath + "fetch";

    // should get all ship
    @Test
    public void should_get_all_ships() throws Exception{
        List<Ship> ship = mockModel();

        mockMvc.perform(get(path)
                .content(mapper.writeValueAsString(ship))
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

    // should throw error when id not found
    @Test
    public void should_throw_exception_when_id_notFound() throws Exception{
        Ship ship= Ship.builder().id(1).name("test").code("aaaa-1111-a1").length(12).width(34).build();

         mockMvc.perform(get(contextPath+"findById/{id}",10)
                 .content(mapper.writeValueAsString(ship))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode", CoreMatchers.is(404)))
                .andExpect(jsonPath("$.errorMessage", CoreMatchers.is("Ship id 10 is not found")));

    }

    // should get ship with id
    @Test
    public void should_getShip_by_id() throws Exception{
        Ship ship= Ship.builder().id(1).name("test").code("aaaa-1111-a1").length(12).width(34).build();
        shipRespository.save(ship);

        mockMvc.perform(get(contextPath+"findById/{id}",1)
                .content(mapper.writeValueAsString(ship))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
