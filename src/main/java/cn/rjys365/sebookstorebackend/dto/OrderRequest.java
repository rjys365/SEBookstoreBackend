package cn.rjys365.sebookstorebackend.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private Long userId;
    private Integer bookId;

    public OrderRequest(){}

    public OrderRequest(Long userId, Integer bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }
}
