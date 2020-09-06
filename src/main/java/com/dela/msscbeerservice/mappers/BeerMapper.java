package com.dela.msscbeerservice.mappers;

import com.dela.msscbeerservice.domain.Beer;
import com.dela.brewery.models.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = { DateMapper.class })
@DecoratedWith(BeerDecoratorMapper.class)
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDto beerDto);
    BeerDto beerToBeerDtoWithInventory(Beer beer);
    BeerDto beerToBeerDto(Beer beer);
}
