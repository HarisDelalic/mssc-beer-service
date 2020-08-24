package com.dela.msscbeerservice.web.services;

import com.dela.msscbeerservice.domain.Customer;
import com.dela.msscbeerservice.repositories.CustomerRepository;
import com.dela.msscbeerservice.web.models.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> getCustomers() {
        List<Customer> customers =  customerRepository.findAll();

        return customers.stream().map(customer -> customer.toDto()).collect(Collectors.toList());
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        Customer savedCustomer = customerRepository.save(customerDto.toCustomer());

        return savedCustomer.toDto();
    }
}
