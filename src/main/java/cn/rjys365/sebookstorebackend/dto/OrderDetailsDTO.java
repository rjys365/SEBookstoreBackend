package cn.rjys365.sebookstorebackend.dto;

import cn.rjys365.sebookstorebackend.entities.Order;
import cn.rjys365.sebookstorebackend.entities.OrderItem;
import lombok.Getter;

import java.util.*;

@Getter
public class OrderDetailsDTO {
    private Set<OrderItemDTO> items;

    private Integer id;

    private Long userId;

    private OrderDigest digest;

    public OrderDetailsDTO(){}

    public OrderDetailsDTO(Order order){
        this.id = order.getId();
        this.userId = order.getUserId();
        this.items=new HashSet<OrderItemDTO>();
        Set<OrderItem> items = order.getItems();
        for (OrderItem item : items){
            this.items.add(new OrderItemDTO(item));
        }
        this.digest =new OrderDigest(order);
    }
}
