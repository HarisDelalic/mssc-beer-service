package com.dela.msscbeerservice.web.listeners;

import com.dela.brewery.events.OrderValidationRequest;
import com.dela.brewery.events.OrderValidationResponse;
import com.dela.brewery.models.beerOrder.BeerOrderDto;
import com.dela.brewery.models.beerOrder.OrderValidationResultDto;
import com.dela.msscbeerservice.config.JmsConfig;
import com.dela.msscbeerservice.validators.OrderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderValidationListener {

    private final JmsTemplate jmsTemplate;
    private final OrderValidator orderValidator;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_REQUEST_QUEUE)
    public void listenAndResponse(OrderValidationRequest request) {
        BeerOrderDto beerOrderDto = request.getBeerOrderDto();

        boolean isValid = orderValidator.validate(beerOrderDto);

        OrderValidationResultDto orderValidationResultDto = new OrderValidationResultDto(
                beerOrderDto.getId(),
                isValid
        );

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESULT_QUEUE,
                new OrderValidationResponse(orderValidationResultDto));
    }
}
