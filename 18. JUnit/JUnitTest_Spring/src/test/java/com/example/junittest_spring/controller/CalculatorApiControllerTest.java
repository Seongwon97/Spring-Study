package com.example.junittest_spring.controller;

import com.example.junittest_spring.component.Calculator;
import com.example.junittest_spring.component.DollarCalculator;
import com.example.junittest_spring.component.MarketApi;
import com.example.junittest_spring.dto.Req;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// web에서 CalculatorApiController.class를 테스트하겠다.
// (DollerCalculatorTest에서 @SpringBootTest는 모든 Bean을 추가하지만 이렇게 하나만 추가하면 자원을 줄일 수 있다.)
@WebMvcTest(CalculatorApiController.class)
@AutoConfigureWebMvc
@Import({Calculator.class, DollarCalculator.class})
public class CalculatorApiControllerTest {
    @MockBean
    private MarketApi marketApi; // mockBean으로 먼저 추가

    @Autowired
    private MockMvc mockMvc; // MVC를 Mock으로 테스트하겠다~

    @BeforeEach
    public void init(){
        Mockito.when(marketApi.connect()).thenReturn(3000);
        // mockBean으로 지정된 marketApi가 실행되면 3000을 return
    }

    @Test
    public void sumTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/sum")
                        .queryParam("x", "10")
                        .queryParam("y", "10")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().string("60000")
        ).andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void minusTest() throws Exception {
        // Request를 보내야해서 Request객체 생성
        Req req = new Req();
        req.setX(10);
        req.setY(10);

        // objectMapper를 사용하여 req를 json으로 변경
        String json = new ObjectMapper().writeValueAsString(req);

        mockMvc.perform(
                MockMvcRequestBuilders.post("http://localhost:8080/api/minus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.result").value("0") // result의 값이 0인지 체크
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.response.resultCode").value("Ok") // response의 resultCode값이 Ok인지 체크
        ).andDo(MockMvcResultHandlers.print());
    }
}
