package com.dela.msscbeerservice.web.services;

import com.dela.msscbeerservice.domain.Beer;
import com.dela.msscbeerservice.mappers.BeerMapper;
import com.dela.msscbeerservice.repositories.BeerRepository;
import com.dela.msscbeerservice.web.models.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        Beer savedBeer = beerRepository.save(beerMapper.beerDtoToBeer(beerDto));

        return beerMapper.beerToBeerDto(savedBeer);
    }
}
