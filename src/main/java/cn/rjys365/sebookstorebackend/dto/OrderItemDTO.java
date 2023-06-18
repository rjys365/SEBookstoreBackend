package cn.rjys365.sebookstorebackend.dto;

import cn.rjys365.sebookstorebackend.entities.OrderItem;
import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class OrderItemDTO {

    private Integer id;//book id

    private Integer count;

    private Double price;//price for each book

    private String title;

    private Double subTotal;

    public OrderItemDTO(OrderItem orderItem){
        this.id = orderItem.getId();
        this.count = orderItem.getCount();
        this.price = orderItem.getPrice();
        this.title = orderItem.getTitle();
        this.subTotal = orderItem.getSubTotal();
    }
}
