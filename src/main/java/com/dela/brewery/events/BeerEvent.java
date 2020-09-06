package com.dela.brewery.events;

import com.dela.brewery.models.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -8222542150576949094L;

    private BeerDto beerDto;
}
