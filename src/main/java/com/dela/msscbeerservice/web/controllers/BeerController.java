package com.dela.msscbeerservice.web.controllers;

import com.dela.msscbeerservice.web.models.BeerDto;
import com.dela.msscbeerservice.web.models.BeerPagedList;
import com.dela.msscbeerservice.web.models.BeerStyleEnum;
import com.dela.msscbeerservice.web.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;
    private final BeerService beerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BeerPagedList> getBeers(
            @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyleEnum,
            @RequestParam(value = "beerName", required = false) String beerName,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return new ResponseEntity<BeerPagedList>(
                beerService.getBeers(beerName, beerStyleEnum, PageRequest.of(pageNumber, pageSize), showInventoryOnHand),
                HttpStatus.OK);
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@NotNull @PathVariable("beerId") UUID beerId,
                                               @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {

        return new ResponseEntity<BeerDto>(beerService.findById(beerId, showInventoryOnHand), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDto> saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
        BeerDto savedBeerDto = beerService.saveNewBeer(beerDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beers/" + savedBeerDto.getId());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDto> updateBeer(@NotNull @PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto) {

        beerService.updateBeer(beerId, beerDto);

        return new ResponseEntity<BeerDto>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity<BeerDto> deleteBeer(@PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
