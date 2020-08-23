package com.dela.msscbeerservice.domain;

import com.dela.msscbeerservice.web.models.BeerDto;
import com.dela.msscbeerservice.web.models.BeerStyleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Version
    private Integer version;
    @Column(name = "beer_name")
    private String beerName;

    @Column(name = "beer_style")
    @Enumerated(EnumType.STRING)
    private BeerStyleEnum beerStyle;

    @Column(name = "upc")
    private Long upc;
    @Column(name = "quantity")
    private Integer quantityOnHand;
    @Column(name = "price")
    private BigDecimal price;

    @CreationTimestamp
    @Column(updatable = false)
    private OffsetDateTime createDate;

    @UpdateTimestamp
    private OffsetDateTime lastModifiedDate;

    public UUID getId() {
        return id;
    }

    public BeerDto toDto() {
        BeerDto beerDto = BeerDto.builder()
                .version(this.version)
                .beerName(this.beerName)
                .beerStyle(this.beerStyle)
                .upc(this.upc)
                .quantityOnHand(this.quantityOnHand)
                .price(this.price)
                .build();
        return beerDto;
    }
}
