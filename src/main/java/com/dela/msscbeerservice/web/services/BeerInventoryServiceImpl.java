package com.dela.msscbeerservice.web.services;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@ConfigurationProperties(value = "com.dela", ignoreUnknownFields = false)
public class BeerInventoryServiceImpl implements BeerInventoryService {

    private final RestTemplate restTemplate;
    private String inventoryServiceLocationApi;
    private String totalQuantityApi;


    public void setInventoryServiceLocationApi(String inventoryServiceLocationApi) {
        this.inventoryServiceLocationApi = inventoryServiceLocationApi;
    }

    public void setTotalQuantityApi(String totalQuantityApi) {
        this.totalQuantityApi = totalQuantityApi;
    }

    public BeerInventoryServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public int getQuantityOnHand(UUID beerId) {
        ResponseEntity<Integer> responseEntity = restTemplate.exchange(inventoryServiceLocationApi + totalQuantityApi,
                HttpMethod.GET, null, new ParameterizedTypeReference<Integer>() {}, (Object) beerId);
        return responseEntity.getBody();
    }
}
