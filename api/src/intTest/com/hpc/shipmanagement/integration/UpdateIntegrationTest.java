package com.hpc.shipmanagement.integration;

import com.hpc.shipmanagement.CommonUtilIntegrationTest;
import com.hpc.shipmanagement.entity.Ship;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateIntegrationTest extends CommonUtilIntegrationTest {

    String path = contextPath +"update/{id}";

    // should throw error when id not found
    @Test
    public void should_throw_error_when_Id_not_found() throws Exception {
        Ship ship = mockModelData();
        Ship ship1 = Ship.builder().name("test").code("xxxx-1432-x1").length(23).width(90).build();

        shipRespository.save(ship);

        mockMvc.perform(put(path, 10)
                .content(mapper.writeValueAsString(ship1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode", CoreMatchers.is(404)))
                .andExpect(jsonPath("$.errorMessage", CoreMatchers.is("Ship id 10 is not found")));
    }

    // should throw error when code format not valid
    @Test
    public void should_throw_error_when_code_format_is_invalid() throws Exception {
        Ship ship = mockModelData();
        Ship ship1 = Ship.builder()
                .id(1)
                .name(name)
                .code("test2")
                .build();
        shipRespository.save(ship);

        mockMvc.perform(put(path,1L)
                .content(mapper.writeValueAsString(ship1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", CoreMatchers.is(400)))
                .andExpect(jsonPath("$.errorMessage", CoreMatchers.is("Ship code inValid")));
    }

    // should throw error when ship name duplicate
    @Test
    public void should_throw_error_when_name_duplicate() throws Exception {
        Ship ship = mockModelData();
        Ship ship2 = Ship.builder().id(2).name("test3").code("test-1234-t1").length(45).width(32).build();
        Ship ship1 = Ship.builder().id(2).name(name1).code("ssss-4321-s1").length(45).width(32).build();
        shipRespository.saveAll(Arrays.asList(ship,ship2));

        mockMvc.perform(put(path,2)
                .content(mapper.writeValueAsString(ship1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", CoreMatchers.is(400)))
                .andExpect(jsonPath("$.errorMessage", CoreMatchers.is("Ship code inValid")));
    }
}
