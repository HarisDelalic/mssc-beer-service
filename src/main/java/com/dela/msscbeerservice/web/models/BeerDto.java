package com.dela.msscbeerservice.web.models;

import com.dela.msscbeerservice.domain.Beer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
    @Null
    private UUID id;
    @NotNull
    private Integer version;
    @NotBlank
    private String beerName;
    @NotNull
    private BeerStyleEnum beerStyle;
    @Positive
    private Long upc;
    private Integer quantityOnHand;
    @Positive
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
