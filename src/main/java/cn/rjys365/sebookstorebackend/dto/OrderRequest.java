package cn.rjys365.sebookstorebackend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderRequest {
    private Long userId;
    private Integer bookId;
    private UUID uuid;

    public OrderRequest(){}

    public OrderRequest(Long userId, Integer bookId) {
        this.userId = userId;
        this.bookId = bookId;
        this.uuid = UUID.randomUUID();
    }
}
