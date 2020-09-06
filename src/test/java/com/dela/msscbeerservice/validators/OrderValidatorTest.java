package com.dela.msscbeerservice.validators;

import com.dela.brewery.models.beerOrder.BeerOrderDto;
import com.dela.brewery.models.beerOrder.BeerOrderLineDto;
import com.dela.msscbeerservice.domain.Beer;
import com.dela.msscbeerservice.repositories.BeerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class OrderValidatorTest {

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    OrderValidator orderValidator;

    @BeforeEach
    void setUp() {
        beerRepository.deleteAll();

        List<Beer> beers = new ArrayList<>();
        beers.add(Beer.builder().upc("123456789").build());
        beers.add(Beer.builder().upc("987654321").build());
        beers.add(Beer.builder().upc("111111111").build());
        beers.add(Beer.builder().upc("222222222").build());
        beerRepository.saveAll(beers);
    }

    @Test
    void when_allOrderLinesAreValid_returnsTrue() {

        List<BeerOrderLineDto> beerOrderLines = new ArrayList<>();
        beerOrderLines.add(BeerOrderLineDto.builder().upc("123456789").build());
        beerOrderLines.add(BeerOrderLineDto.builder().upc("111111111").build());
        BeerOrderDto beerOrderDto = BeerOrderDto.builder().beerOrderLines(beerOrderLines).build();

        boolean isValid = orderValidator.validate(beerOrderDto);

        assertTrue(isValid);
    }

    @Test
    void when_anyOrderLineIsInvalid_returnsFalse() {

        List<BeerOrderLineDto> beerOrderLines = new ArrayList<>();
        beerOrderLines.add(BeerOrderLineDto.builder().upc("123456789").build());
        beerOrderLines.add(BeerOrderLineDto.builder().upc("111111111").build());
        beerOrderLines.add(BeerOrderLineDto.builder().upc("555555555").build());
        BeerOrderDto beerOrderDto = BeerOrderDto.builder().beerOrderLines(beerOrderLines).build();

        boolean isValid = orderValidator.validate(beerOrderDto);

        assertFalse(isValid);
    }
}