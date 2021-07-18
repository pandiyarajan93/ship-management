package com.hpc.shipmanagement.integration;

import com.hpc.shipmanagement.CommonUtilIntegrationTest;
import com.hpc.shipmanagement.entity.Ship;
import com.hpc.shipmanagement.model.RequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateIntegrationTest extends CommonUtilIntegrationTest {

    private String url = contextPath + "/save";

    //should throw error when ship name is duplicate
    @Test
    public void should_throw_error_when_name_is_duplicate() throws Exception {
        RequestDto dto1 = RequestDto.builder().code(code).length(length).width(width).name(name).build();

        mockMvc.perform(post(url)
                .content(mapper.writeValueAsString(dto1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        RequestDto dto2 = RequestDto.builder().code("xxxx-0000-x20").length(length).width(width).name(name).build();

        mockMvc.perform(post(url)
                .content(mapper.writeValueAsString(dto2))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(400)))
                .andExpect(jsonPath("$.errorMessage", is("Ship Code Invalid")));
    }


    //should throw error when ship code duplicate
    @Test
    public void should_throw_error_when_code_is_duplicate() throws Exception {
        RequestDto dto1 = mockDto();

        mockMvc.perform(post(url)
                .content(mapper.writeValueAsString(dto1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        RequestDto dto2 = RequestDto.builder().code(code).length(length).width(width).name(name1).build();

        mockMvc.perform(post(url)
                .content(mapper.writeValueAsString(dto2))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(400)))
                .andExpect(jsonPath("$.errorMessage", is("Ship code / name already exits")));
    }

    // should create new ship
    @Test
    public void should_create_ship() throws Exception {
        RequestDto dto = mockDto();

        mockMvc.perform(post(url)
                .content(mapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        List<Ship> listOfShips = shipRespository.findAll();

        assertThat(listOfShips).hasSize(1);
    }

    // should throw error when ship code format not valid
    @Test
    public void should_throw_error_when_code_format_is_invalid() throws Exception {
        RequestDto dto = RequestDto.builder()
                .name(name)
                .code("test")
                .build();

        mockMvc.perform(post(url)
                .content(mapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.errorCode", is(400)))
                .andExpect(jsonPath("$.errorMessage", is("Ship Code Invalid")));
    }
}
