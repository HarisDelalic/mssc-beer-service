package com.dela.msscbeerservice.web.services;

import com.dela.msscbeerservice.domain.Beer;
import com.dela.msscbeerservice.mappers.BeerMapper;
import com.dela.msscbeerservice.repositories.BeerRepository;
import com.dela.msscbeerservice.web.models.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto findById(UUID beerId) {
        return beerRepository.findById(beerId).map(beerMapper::beerToBeerDto).orElse(null);
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        Beer savedBeer = beerRepository.save(beerMapper.beerDtoToBeer(beerDto));

        return beerMapper.beerToBeerDto(savedBeer);
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        beerRepository.findById(beerId).ifPresent(foundBeer ->
        {
            foundBeer.setBeerName(beerDto.getBeerName());
            foundBeer.setBeerStyle(beerDto.getBeerStyle());
            foundBeer.setUpc(beerDto.getUpc());
            foundBeer.setQuantityOnHand(beerDto.getQuantityOnHand());
            foundBeer.setPrice(beerDto.getPrice());
            beerRepository.save(foundBeer);
        });

    }
}
