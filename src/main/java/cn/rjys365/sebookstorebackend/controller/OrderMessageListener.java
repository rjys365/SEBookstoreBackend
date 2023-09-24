package cn.rjys365.sebookstorebackend.controller;

import cn.rjys365.sebookstorebackend.dto.OrderDetailsDTO;
import cn.rjys365.sebookstorebackend.dto.OrderRequest;
import cn.rjys365.sebookstorebackend.service.OrderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class OrderMessageListener {
    private final OrderService orderService;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderMessageListener(OrderService orderService, KafkaTemplate<String, Object> kafkaTemplate) {
        this.orderService = orderService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "order_request", groupId = "bookstore_backend")
    public void listenOrder(OrderRequest orderRequest) {
        if (orderRequest.getUserId() == null) return;
        if (orderRequest.getBookId() == null) {
            var orderOptional= this.orderService.createOrderFromUserCartItems(orderRequest.getUserId().intValue());
            if(orderOptional.isPresent()){
                System.out.println("Order created from cart by user"+orderRequest.getUserId());
                kafkaTemplate.send("order_response",new OrderDetailsDTO(orderOptional.get()));
            }
        }
        else {
            var orderOptional=this.orderService.createOrderFromItem(orderRequest.getUserId().intValue(), orderRequest.getBookId().intValue(), 1);
            if(orderOptional.isPresent()){
                System.out.println("Order created from item by user"+orderRequest.getUserId());
                kafkaTemplate.send("order_response",new OrderDetailsDTO(orderOptional.get()));
            }
        }
    }
}
