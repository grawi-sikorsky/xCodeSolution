package pl.jsikora.xcodesolution.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import pl.jsikora.xcodesolution.dto.RequestCurrencyDTO;
import pl.jsikora.xcodesolution.dto.RequestNumbersDTO;
import pl.jsikora.xcodesolution.dto.ResponseNumbersDTO;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class XcodeControllerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeAll
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    public String prepareNumbersRequest() throws JsonProcessingException {
        RequestNumbersDTO request = new RequestNumbersDTO();
        request.setOrder("ASC");
        request.setNumbers(Arrays.asList(1, 2, 3, 0));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();

        return objectWriter.writeValueAsString(request);
    }

    public String prepareCurrencyRequest() throws JsonProcessingException {
        RequestCurrencyDTO request = new RequestCurrencyDTO();
        request.setCurrency("CHF");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();

        return objectWriter.writeValueAsString(request);
    }

    @Test
    public void pingpong_should_pong() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/status/ping"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        assertEquals("pong", result);
    }

    @Test
    public void numbers_should_return_ok() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/numbers/sort-command")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(prepareNumbersRequest()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        ResponseNumbersDTO response = new ObjectMapper().readValue(result, ResponseNumbersDTO.class);

        assertEquals(Arrays.asList(0, 1, 2, 3), response.getNumbers());
    }

    @Test
    public void currency_should_return_ok() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/currencies/get-current-currency-value-command")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(prepareCurrencyRequest()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        //String result = mvcResult.getResponse().getContentAsString();
        //ResponseNumbersDTO response = new ObjectMapper().readValue(result, ResponseNumbersDTO.class);

        //assertEquals(Arrays.asList(0,1,2,3), response.getNumbers());
    }

}
