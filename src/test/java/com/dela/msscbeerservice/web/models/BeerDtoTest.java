package com.dela.msscbeerservice.web.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
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
                .upc(1234512345L)
                .quantityOnHand(123)
                .price(new BigDecimal(23))
                .createDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
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
                "\"upc\":1234512345,\"quantityOnHand\":123," +
                "\"price\":\"23\"," +
                "\"createDate\":\"2020-08-29T01:53:22+0200\",\"lastModifiedDate\":\"2020-08-29T01:53:22+0200\"}\n";

        BeerDto dtoFromJson = objectMapper.readValue(asJson, BeerDto.class);

        System.out.println(dtoFromJson);
    }
}