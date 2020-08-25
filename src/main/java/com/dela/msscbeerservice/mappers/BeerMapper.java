package com.dela.msscbeerservice.mappers;

import com.dela.msscbeerservice.domain.Beer;
import com.dela.msscbeerservice.web.models.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = { DateMapper.class })
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDto beerDto);
    BeerDto beerToBeerDto(Beer beer);
}
