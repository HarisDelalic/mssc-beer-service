package com.dela.msscbeerservice.web.services.brew_service;

import com.dela.msscbeerservice.config.JmsConfig;
import com.dela.msscbeerservice.domain.Beer;
import com.dela.msscbeerservice.events.BrewBeerEvent;
import com.dela.msscbeerservice.mappers.BeerMapper;
import com.dela.msscbeerservice.repositories.BeerRepository;
import com.dela.msscbeerservice.web.services.BeerInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BreweryServiceImpl implements BreweryService {
    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Override
    @Scheduled(fixedRate = 5000)
    public void assureAvailability() {
        log.debug("checking started");
        beerRepository.findAll().forEach((beer -> checkQuantityOnHandAndSendBrewingEvent(beer)));
    }

    private void checkQuantityOnHandAndSendBrewingEvent(Beer beer) {
        Integer quantityOnHand = beerInventoryService.getQuantityOnHand(beer.getId());

        if(quantityOnHand <= beer.getMinOnHand()) {
            jmsTemplate
                    .convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE,
                            new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));

        }
    }
}
