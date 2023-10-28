package cn.rjys365.sebookstorebackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBookStatsDTO {
    private Integer userId;
    private Long totalBooks;
    private Double totalAmount;

    public UserBookStatsDTO(Integer userId, Long totalBooks, Double totalAmount) {
        this.userId = userId;
        this.totalBooks = totalBooks;
        this.totalAmount = totalAmount;
    }
}
