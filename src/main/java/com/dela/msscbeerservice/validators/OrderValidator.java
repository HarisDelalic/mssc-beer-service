package com.dela.msscbeerservice.validators;

import com.dela.brewery.models.beer_order.BeerOrderDto;
import com.dela.msscbeerservice.repositories.BeerRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Data
public class OrderValidator {

    private final BeerRepository beerRepository;

    public boolean validate(BeerOrderDto beerOrderDto) {
        return beerOrderDto.getBeerOrderLines().stream()
                .allMatch(beerOrderLineDto -> beerRepository.findByUpc(beerOrderLineDto.getUpc()) != null);
    }

}
