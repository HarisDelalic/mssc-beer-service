package com.dela.msscbeerservice.mappers;

import com.dela.msscbeerservice.domain.Beer;
import com.dela.brewery.models.BeerDto;
import com.dela.msscbeerservice.web.services.BeerInventoryService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerDecoratorMapper implements BeerMapper {

    private BeerMapper beerMapper;
    private BeerInventoryService beerInventoryService;

    @Autowired
    public void setBeerMapper(BeerMapper beerMapper) {
        this.beerMapper = beerMapper;
    }

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    public BeerDto beerToBeerDtoWithInventory(Beer beer) {
        BeerDto dto = beerMapper.beerToBeerDtoWithInventory(beer);
        dto.setQuantityOnHand(beerInventoryService.getQuantityOnHand(beer.getId()));
        return dto;
    }

    public BeerDto beerToBeerDto(Beer beer) {
        return beerMapper.beerToBeerDtoWithInventory(beer);
    }

    public Beer beerDtoToBeer(BeerDto beerDto) {
        return beerMapper.beerDtoToBeer(beerDto);
    }
}
