package com.dela.msscbeerservice.web.services.brew_service;

import com.dela.msscbeerservice.config.JmsConfig;
import com.dela.msscbeerservice.domain.Beer;
import com.dela.events.BrewBeerEvent;
import com.dela.events.NewInventoryEvent;
import com.dela.msscbeerservice.repositories.BeerRepository;
import com.dela.msscbeerservice.web.models.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BreweryListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void createNewInventory(BrewBeerEvent event) {
        BeerDto beerDto = event.getBeerDto();
        Beer beer = beerRepository.findById(beerDto.getId()).get();

//        Since there is low level of beer available, we are going to increase that amount.
//        Amount will be increased by creating new beerInventory in beer_inventory_service
        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        log.info("creating new inventory for beer with ID: " + beerDto.getId() + ", name: " + beerDto.getBeerName());
        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, new NewInventoryEvent(beerDto));
    }
}
