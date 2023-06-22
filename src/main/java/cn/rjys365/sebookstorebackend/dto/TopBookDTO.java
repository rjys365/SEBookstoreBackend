package cn.rjys365.sebookstorebackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopBookDTO {
    private int bookId;
    private String title;
    private long totalQuantity;

    public TopBookDTO(int bookId, String title, long totalQuantity) {
        this.bookId = bookId;
        this.title = title;
        this.totalQuantity = totalQuantity;
    }
}