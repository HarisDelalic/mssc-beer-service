package com.dela.msscbeerservice.web.controllers;

import com.dela.msscbeerservice.repositories.CustomerRepository;
import com.dela.msscbeerservice.web.models.CustomerDto;
import com.dela.msscbeerservice.web.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
@Validated
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers() {

        log.debug("in getCustomers ...");

        return new ResponseEntity<List<CustomerDto>>(customerService.getCustomers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> saveNewCustomer(@Valid @RequestBody CustomerDto customerDto) {

        log.debug("in saveNewCustomer, saving customer: " + customerDto.toCustomer());
        return new ResponseEntity<CustomerDto>(customerService.saveNewCustomer(customerDto), HttpStatus.CREATED);
    }
}
