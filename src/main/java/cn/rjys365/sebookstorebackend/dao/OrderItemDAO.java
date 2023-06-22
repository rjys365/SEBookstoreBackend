package cn.rjys365.sebookstorebackend.dao;

import cn.rjys365.sebookstorebackend.dto.TopBookDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderItemDAO {
    List<TopBookDTO> findTopBooksByTotalCountBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    List<TopBookDTO> findTopBooksByUserIdAndTotalCountBetweenDates(LocalDateTime startDate, LocalDateTime endDate,Integer userId);
}
