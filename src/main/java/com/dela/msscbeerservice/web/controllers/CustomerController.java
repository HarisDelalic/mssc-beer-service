package com.dela.msscbeerservice.web.controllers;

import com.dela.msscbeerservice.repositories.CustomerRepository;
import com.dela.msscbeerservice.web.models.CustomerDto;
import com.dela.msscbeerservice.web.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/customers")
@Validated
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers() {

        return new ResponseEntity<List<CustomerDto>>(customerService.getCustomers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> saveNewCustomer(@Valid @RequestBody CustomerDto customerDto) {

        return new ResponseEntity<CustomerDto>(customerService.saveNewCustomer(customerDto), HttpStatus.CREATED);
    }
}
