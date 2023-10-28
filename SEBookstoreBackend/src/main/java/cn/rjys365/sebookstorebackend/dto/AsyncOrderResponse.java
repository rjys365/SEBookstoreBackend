package cn.rjys365.sebookstorebackend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AsyncOrderResponse {
    private UUID uuid;
    private OrderDetailsDTO orderDetailsDTO;

    public AsyncOrderResponse() {
    }

    public AsyncOrderResponse(UUID uuid, OrderDetailsDTO orderDetailsDTO) {
        this.uuid = uuid;
        this.orderDetailsDTO = orderDetailsDTO;
    }

    public AsyncOrderResponse(UUID uuid){
        this.uuid = uuid;
        this.orderDetailsDTO=null;
    }
}
