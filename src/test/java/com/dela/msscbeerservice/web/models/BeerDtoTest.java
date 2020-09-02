package com.dela.msscbeerservice.web.models;

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
        String asJson = "{\"beerId\":\"ac8ce8a3-f88a-43b6-987d-695fde2d019b\",\"version\":3," +
                "\"beerName\":\"karlovacko\",\"beerStyle\":\"LAGER\"," +
                "\"upc\":\"" + BeerUpcLoader.BEER_1_UPC + "\",\"quantityOnHand\":123," +
                "\"price\":\"23\"," +
                "\"createDate\":\"2020-08-29 01:53:22.000000\",\"lastModifiedDate\":\"2020-08-29 01:53:22\"}\n";

        BeerDto dtoFromJson = objectMapper.readValue(asJson, BeerDto.class);

        System.out.println(dtoFromJson);
    }
}