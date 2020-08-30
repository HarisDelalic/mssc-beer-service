package com.dela.msscbeerservice.web.services;

import com.dela.msscbeerservice.web.models.BeerDto;
import com.dela.msscbeerservice.web.models.BeerPagedList;
import com.dela.msscbeerservice.web.models.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerDto findById(UUID beerId, Boolean showInventoryOnHand);

    BeerPagedList getBeers(String beerName, BeerStyleEnum beerStyleEnum, PageRequest pageRequest, Boolean showInventoryOnHand);
}
