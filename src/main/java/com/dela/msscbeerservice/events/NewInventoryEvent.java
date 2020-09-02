package com.dela.msscbeerservice.events;

import com.dela.msscbeerservice.web.models.BeerDto;

public class NewInventoryEvent extends BeerEvent {

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
