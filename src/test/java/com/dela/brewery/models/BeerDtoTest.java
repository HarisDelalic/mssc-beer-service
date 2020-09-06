package com.dela.brewery.models;

import com.dela.msscbeerservice.bootstrap.BeerUpcLoader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonTest
class BeerDtoTest {

    @Autowired
    ObjectMapper objectMapper;

    BeerDto beerDto;

    @BeforeEach
    void setUp() {
        beerDto = BeerDto.builder()
                .id(UUID.randomUUID())
                .version(3)
                .beerName("karlovacko")
                .beerStyle(BeerStyleEnum.LAGER)
                .upc(BeerUpcLoader.BEER_1_UPC)
                .quantityOnHand(123)
                .price(new BigDecimal(23))
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
    }

    @Test
    void serialize() throws JsonProcessingException {
        String asJson = objectMapper.writeValueAsString(beerDto);

        System.out.println(asJson);
    }

    @Test
    void deserialize() throws JsonProcessingException {
        String asJson = "{\"version\":3,\"beerName\":\"karlovacko\"," +
                "\"beerStyle\":\"LAGER\",\"upc\":\"0631234200036\"," +
                "\"quantityOnHand\":123,\"price\":\"23\"," +
                "\"createdDate\":\"2020:09:06 18:32:08\"," +
                "\"lastModifiedDate\":\"2020:09:06 18:32:08\"," +
                "\"beerId\":\"1d0248c4-e722-4e95-bf25-781e9ffcbbe8\"}\n";

        BeerDto dtoFromJson = objectMapper.readValue(asJson, BeerDto.class);

        System.out.println(dtoFromJson);
    }
}