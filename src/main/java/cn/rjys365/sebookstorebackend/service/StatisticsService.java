package cn.rjys365.sebookstorebackend.service;

import cn.rjys365.sebookstorebackend.dto.TopBookDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsService {
    public List<TopBookDTO> findTopBooksByTotalCountBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    public List<TopBookDTO> findTopBooksByUserIdAndTotalCountBetweenDates(LocalDateTime startDate, LocalDateTime endDate, Integer userId);
}
