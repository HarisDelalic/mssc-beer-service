package com.dela.msscbeerservice.web.models;

import com.dela.msscbeerservice.domain.Beer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
    private UUID id;
    private Integer version;
    private String beerName;
    private BeerStyleEnum beerStyle;
    private Long upc;
    private Integer quantityOnHand;
    private BigDecimal price;

    private OffsetDateTime createDate;
    private OffsetDateTime lastModifiedDate;

    public Beer toBeer() {
        Beer beer = Beer.builder()
                .version(this.version)
                .beerName(this.beerName)
                .beerStyle(this.beerStyle)
                .upc(this.upc)
                .quantityOnHand(this.quantityOnHand)
                .price(this.price)
                .build();
         return beer;
    }
}
