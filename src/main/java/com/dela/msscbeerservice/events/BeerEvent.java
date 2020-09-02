package com.dela.msscbeerservice.events;

import com.dela.msscbeerservice.web.models.BeerDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Data
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -8222542150576949094L;

    private final BeerDto beerDto;
}
