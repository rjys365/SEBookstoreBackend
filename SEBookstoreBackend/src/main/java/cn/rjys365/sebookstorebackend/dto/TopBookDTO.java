package cn.rjys365.sebookstorebackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopBookDTO {
    private int bookId;
    private String title;
    private long totalCount;

    public TopBookDTO(int bookId, String title, long totalCount) {
        this.bookId = bookId;
        this.title = title;
        this.totalCount = totalCount;
    }
}