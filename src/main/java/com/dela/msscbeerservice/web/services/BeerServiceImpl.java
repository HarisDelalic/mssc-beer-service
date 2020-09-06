package com.dela.msscbeerservice.web.services;

import com.dela.msscbeerservice.domain.Beer;
import com.dela.msscbeerservice.mappers.BeerMapper;
import com.dela.msscbeerservice.repositories.BeerRepository;
import com.dela.brewery.models.BeerDto;
import com.dela.brewery.models.BeerPagedList;
import com.dela.brewery.models.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false || #showInventoryOnHand == null")
    @Override
    public BeerPagedList getBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {
        BeerPagedList beerPagedList = null;
        Page<Beer> beerPage;

        System.out.println("called");
        if (!StringUtils.isEmpty(beerName) && beerStyle != null) {
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && beerStyle == null) {
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && beerStyle != null) {
            beerPage = beerRepository.findAllByBeerStyle(beerName, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }
        beerPagedList = new BeerPagedList(
                beerPage.getContent().stream()
                        .map(beer -> BooleanUtils.isTrue(showInventoryOnHand) ?
                                beerMapper.beerToBeerDtoWithInventory(beer) :
                                beerMapper.beerToBeerDto(beer))
                        .collect(Collectors.toList()),
                PageRequest.of(beerPage.getPageable().getPageNumber(),
                        beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements()
        );

        return beerPagedList;
    }

    @Cacheable(cacheNames = "beerCacheByUpc", key = "#upc", condition = "#showInventoryOnHand == false || #showInventoryOnHand == null")
    @Override
    public BeerDto findByUpc(String upc, Boolean showInventoryOnHand) {
        Beer foundBeer = beerRepository.findByUpc(upc);

        System.out.println("called");

        return result(foundBeer, showInventoryOnHand);
    }

    private BeerDto result(Beer foundBeer, Boolean showInventoryOnHand) {
        return BooleanUtils.isTrue(showInventoryOnHand) ?
                beerMapper.beerToBeerDtoWithInventory(foundBeer) :
                beerMapper.beerToBeerDto(foundBeer);
    }

    @Cacheable(cacheNames = "beerCacheByBeerId", key = "#beerId", condition = "#showInventoryOnHand == false || #showInventoryOnHand == null")
    @Override
    public BeerDto findById(UUID beerId, Boolean showInventoryOnHand) {
        Beer foundBeer = beerRepository.findById(beerId).orElseThrow(RuntimeException::new);

        return result(foundBeer, showInventoryOnHand);
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        Beer savedBeer = beerRepository.save(beerMapper.beerDtoToBeer(beerDto));

        return beerMapper.beerToBeerDtoWithInventory(savedBeer);
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer foundBeer = beerRepository.findById(beerId).orElseThrow(RuntimeException::new);

        foundBeer.setBeerName(beerDto.getBeerName());
        foundBeer.setBeerStyle(beerDto.getBeerStyle());
        foundBeer.setUpc(beerDto.getUpc());
//        foundBeer.setQuantityOnHand(beerDto.getQuantityOnHand());
        foundBeer.setPrice(beerDto.getPrice());

        return beerMapper.beerToBeerDtoWithInventory(beerRepository.save(foundBeer));
    }
}
