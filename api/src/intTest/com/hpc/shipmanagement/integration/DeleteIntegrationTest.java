package com.hpc.shipmanagement.integration;

import com.hpc.shipmanagement.CommonUtilIntegrationTest;
import com.hpc.shipmanagement.entity.Ship;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteIntegrationTest extends CommonUtilIntegrationTest {

    String path =  contextPath+"delete/{id}";

    //should throw error when id not found
    @Test
    public void should_throw_error_when_id_not_found() throws Exception {

        mockMvc.perform(delete(path, 10)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    //should delete all ship
    @Test
    public void should_delete_ships() throws Exception {
        Ship ship= mockModelData();
        Ship ship1 = Ship.builder().id(2).name("test").code("test-1234-t1").length(34).width(65).build();

        shipRespository.saveAll(Arrays.asList(ship,ship1));

        mockMvc.perform(delete(path, 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        List<Ship> ships = shipRespository.findAll();

        assertThat(ships).hasSize(1);
    }
}
