package com.dela.msscbeerservice.repositories;

import com.dela.msscbeerservice.domain.Customer;
import com.dela.msscbeerservice.web.models.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> { }
