package com.dela.msscbeerservice.web.controllers;

import com.dela.msscbeerservice.web.models.BeerDto;
import com.dela.msscbeerservice.web.models.BeerStyleEnum;
import com.dela.msscbeerservice.web.services.BeerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerService beerService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/beers/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = validBeerDto();

        String beerDtoJSON = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any())).willReturn(beerDto);

        mockMvc.perform(post("/api/v1/beers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeer() throws Exception {
        BeerDto beerDto = validBeerDto();

        String beerDtoJSON = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beers/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJSON))
                .andExpect(status().isNoContent());
    }

    private BeerDto validBeerDto() {
        return BeerDto.builder()
                .beerName("Sarajevsko")
                .beerStyle(BeerStyleEnum.IPA)
                .upc(222L)
                .quantityOnHand(2)
                .price(BigDecimal.valueOf(2))
                .build();
    }
}