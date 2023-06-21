package cn.rjys365.sebookstorebackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequest {
    private Integer id;
    private Integer quantity;
}
