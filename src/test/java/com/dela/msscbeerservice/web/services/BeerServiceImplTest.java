package com.dela.msscbeerservice.web.services;

import com.dela.msscbeerservice.bootstrap.BeerUpcLoader;
import com.dela.msscbeerservice.domain.Beer;
import com.dela.msscbeerservice.repositories.BeerRepository;
import com.dela.msscbeerservice.web.models.BeerDto;
import com.dela.msscbeerservice.web.models.BeerStyleEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest
class BeerServiceImplTest {
    private static final String BEER_ID = "0a818933-087d-47f2-ad83-2f986ed087eb";

    @Autowired
    private BeerService beerService;

    @MockBean
    private BeerRepository beerRepository;

    Beer beer;

    @BeforeEach
    void setUp() {
        beer = validBeer();
    }

    @Test
    void when_showInventoryOnHandIsFalse_doNtoShowQuantityOnHand() {
        Boolean showInventoryOnHand = false;
        when(beerRepository.findById(beer.getId())).thenReturn(Optional.of(beer));

        BeerDto beerDto = beerService.findById(beer.getId(), showInventoryOnHand);

        Assertions.assertNull(beerDto.getQuantityOnHand());
    }

    @Test
    void when_showInventoryOnHandIsNotPresent_doNtoShowQuantityOnHand() {
        Boolean showInventoryOnHand = null;
        when(beerRepository.findById(beer.getId())).thenReturn(Optional.of(beer));

        BeerDto beerDto = beerService.findById(beer.getId(), showInventoryOnHand);

        Assertions.assertNull(beerDto.getQuantityOnHand());
    }

    @Test
    void when_showInventoryOnHandIsTrue_showQuantityOnHand() {
        Boolean showInventoryOnHand = true;
        when(beerRepository.findById(beer.getId())).thenReturn(Optional.of(beer));

        BeerDto beerDto = beerService.findById(beer.getId(), showInventoryOnHand);

        Assertions.assertNotNull(beerDto.getQuantityOnHand());
    }

    private Beer validBeer() {
        return Beer.builder()
                .id(UUID.fromString(BEER_ID))
                .beerName("Sarajevsko")
                .beerStyle(BeerStyleEnum.IPA)
                .upc(BeerUpcLoader.BEER_1_UPC)
                .price(BigDecimal.valueOf(2))
                .build();
    }

}