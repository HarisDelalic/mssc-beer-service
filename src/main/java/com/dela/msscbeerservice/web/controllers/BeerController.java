package com.dela.msscbeerservice.web.controllers;

import com.dela.msscbeerservice.web.models.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {

        return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDto> saveNewBeer(@RequestBody BeerDto beerDto) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDto> updateBeer(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {

        return new ResponseEntity<BeerDto>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{upc}")
    public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable("upc") Long upc) {

        return new ResponseEntity<BeerDto>(BeerDto.builder().build(), HttpStatus.OK);
    }
}
