package com.dela.msscbeerservice.web.models;

import com.dela.msscbeerservice.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private UUID id;

    @Length(min = 3, max = 100)
    private String name;

    public Customer toCustomer() {
        return Customer.builder()
                .id(this.id)
                .name(name)
                .build();
    }
}
