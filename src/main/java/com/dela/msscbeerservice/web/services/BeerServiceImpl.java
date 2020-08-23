package com.dela.msscbeerservice.web.services;

import com.dela.msscbeerservice.domain.Beer;
import com.dela.msscbeerservice.repositories.BeerRepository;
import com.dela.msscbeerservice.web.models.BeerDto;
import org.springframework.stereotype.Service;

@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;

    public BeerServiceImpl(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        Beer savedBeer = beerRepository.save(beerDto.toBeer());

        return savedBeer.toDto();
    }
}
