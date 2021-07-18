package com.hpc.shipmanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hpc.shipmanagement.entity.Ship;
import com.hpc.shipmanagement.model.RequestDto;
import com.hpc.shipmanagement.repository.ShipRespository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
                classes = ShipManagementApplication.class)
public class CommonUtilIntegrationTest {

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected ShipRespository shipRespository;

    @Autowired
    protected EntityManager entityManager;

    @Autowired
    protected ObjectMapper mapper;

    protected MockMvc mockMvc;

    protected  final String contextPath ="/ship/";


    protected final String name = "Ship1";
    protected final String name1 = "Ship2";
    protected final String code = "AAAA-1111-A1";
    protected final double length = 9d;
    protected final double width = 9.8;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        shipRespository.deleteAll();
    }


    protected String[] getErrorMessages(MvcResult mvcResult) throws UnsupportedEncodingException, JSONException {
        String response = mvcResult.getResponse().getContentAsString();
        JSONObject json = new JSONObject(response);
        json.remove("errorCode");
        return json.get("message").toString().split(",");
    }
    protected List<Ship> mockModel(){

        Ship ship = Ship.builder()
                .id(1)
                .name(name)
                .code(code)
                .length(length)
                .width(width)
                .build();

        Ship ship1 = Ship.builder()
                .id(2)
                .name(name1)
                .code(code)
                .length(length)
                .width(width)
                .build();
        return Arrays.asList(ship,ship1);
    }

    protected RequestDto mockDto(){
        return RequestDto.builder()
                .name(name)
                .code(code)
                .length(length)
                .width(width)
                .build();
    }

    protected Ship mockModelData(){
        return Ship.builder()
                .id(1)
                .name(name1)
                .code(code)
                .length(length)
                .width(width)
                .build();
    }
}
