package com.dela.msscbeerservice.web.services;

import com.dela.msscbeerservice.web.models.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getCustomers();

    CustomerDto saveNewCustomer(CustomerDto customerDto);
}
