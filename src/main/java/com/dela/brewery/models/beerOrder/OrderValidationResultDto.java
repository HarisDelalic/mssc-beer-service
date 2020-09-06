package com.dela.brewery.models.beerOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class OrderValidationResultDto {
    private UUID id;
    private boolean isValid;
}
