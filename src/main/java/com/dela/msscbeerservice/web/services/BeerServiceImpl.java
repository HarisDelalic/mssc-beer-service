package com.dela.msscbeerservice.web.services;

import com.dela.msscbeerservice.domain.Beer;
import com.dela.msscbeerservice.mappers.BeerMapper;
import com.dela.msscbeerservice.repositories.BeerRepository;
import com.dela.msscbeerservice.web.models.BeerDto;
import com.dela.msscbeerservice.web.models.BeerPagedList;
import com.dela.msscbeerservice.web.models.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
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

    @Override
    public BeerPagedList getBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {
        BeerPagedList beerPagedList = null;
        Page<Beer> beerPage;

        if(!StringUtils.isEmpty(beerName) && beerStyle != null) {
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if(!StringUtils.isEmpty(beerName) && beerStyle == null) {
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if(StringUtils.isEmpty(beerName) && beerStyle != null) {
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

        return  beerPagedList;
    }

    @Override
    public BeerDto findById(UUID beerId, Boolean showInventoryOnHand) {
        Beer foundBeer = beerRepository.findById(beerId).orElseThrow(RuntimeException::new);

        return BooleanUtils.isTrue(showInventoryOnHand) ?
                beerMapper.beerToBeerDtoWithInventory(foundBeer) :
                beerMapper.beerToBeerDto(foundBeer);
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
