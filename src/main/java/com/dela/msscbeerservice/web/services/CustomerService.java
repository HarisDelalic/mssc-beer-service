package com.dela.msscbeerservice.web.services;

import com.dela.brewery.models.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getCustomers();

    CustomerDto saveNewCustomer(CustomerDto customerDto);
}
