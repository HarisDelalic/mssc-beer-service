package com.dela.msscbeerservice.web.controllers;

import com.dela.brewery.models.CustomerDto;
import com.dela.msscbeerservice.web.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @Test
    public void getCustomers() throws Exception {
        CustomerDto customer1 = CustomerDto.builder().name("cus 1").build();
        CustomerDto customer2 = CustomerDto.builder().name("cus 2").build();

        List<CustomerDto> customers = Stream.of(customer1, customer2).collect(Collectors.toList());

        given(customerService.getCustomers()).willReturn(customers);

        mockMvc.perform(get("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void whenDataIsValid_thenReturnsStatusCreated() throws Exception {
        CustomerDto customer1 = CustomerDto.builder().name("customer1").build();
        String customer1AsJson = objectMapper.writeValueAsString(customer1);

        given(customerService.saveNewCustomer(customer1)).willReturn(customer1);

        mockMvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customer1AsJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenDataIsInvalid_thenReturnsStatusBadRequest() throws Exception {
        CustomerDto customer1 = CustomerDto.builder().name("c1").build();
        String customer1AsJson = objectMapper.writeValueAsString(customer1);

        given(customerService.saveNewCustomer(customer1)).willReturn(customer1);

        mockMvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customer1AsJson))
                .andExpect(status().isBadRequest());
    }
}