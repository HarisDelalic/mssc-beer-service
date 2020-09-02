package com.dela.msscbeerservice.events;

import com.dela.msscbeerservice.web.models.BeerDto;

public class BrewBeerEvent extends BeerEvent {

    static final long serialVersionUID = 264247917491070681L;

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
