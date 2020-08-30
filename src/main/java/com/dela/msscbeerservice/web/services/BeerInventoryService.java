package com.dela.msscbeerservice.web.services;

import java.util.UUID;

public interface BeerInventoryService {
    int getQuantityOnHand(UUID beerId);
}
