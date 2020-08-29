package com.dela.msscbeerservice.bootstrap;

import com.dela.msscbeerservice.domain.Beer;
import com.dela.msscbeerservice.repositories.BeerRepository;
import com.dela.msscbeerservice.web.models.BeerStyleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadInitialBeerObjects();
    }

    private void loadInitialBeerObjects() {
        if(beerRepository.count() == 0) {

            beerRepository.save(
                    Beer.builder()
                            .beerName("Karlovacko")
                            .beerStyle(BeerStyleEnum.LAGER)
                            .quantityOnHand(1)
                            .upc(BEER_1_UPC)
                            .price(BigDecimal.ONE)
                            .build()
            );

            beerRepository.save(
                    Beer.builder()
                            .beerName("Sarajevsko")
                            .beerStyle(BeerStyleEnum.IPA)
                            .quantityOnHand(2)
                            .upc(BEER_2_UPC)
                            .price(BigDecimal.valueOf(2))
                            .build()
            );
            beerRepository.save(
                    Beer.builder()
                            .beerName("Sarajevsko")
                            .beerStyle(BeerStyleEnum.IPA)
                            .quantityOnHand(2)
                            .upc(BEER_3_UPC)
                            .price(BigDecimal.valueOf(2))
                            .build()
            );
        }
    }
}
