package com.dela.msscbeerservice.web.services;

import com.dela.msscbeerservice.web.models.BeerDto;

public interface BeerService {
    BeerDto saveNewBeer(BeerDto beerDto);
}
