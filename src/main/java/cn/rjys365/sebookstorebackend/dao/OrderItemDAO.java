package cn.rjys365.sebookstorebackend.dao;

import cn.rjys365.sebookstorebackend.dto.TopBookDTO;
import cn.rjys365.sebookstorebackend.dto.UserBookStatsDTO;
import cn.rjys365.sebookstorebackend.entities.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderItemDAO {
    List<TopBookDTO> findTopBooksByTotalCountBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    List<TopBookDTO> findTopBooksByUserIdAndTotalCountBetweenDates(LocalDateTime startDate, LocalDateTime endDate,Integer userId);

    List<UserBookStatsDTO> findAllUsersBookStatsByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    UserBookStatsDTO findUserBookStatsByUserIdAndDateRange(LocalDateTime startDate, LocalDateTime endDate, Integer userId);

    OrderItem saveOrderItem(OrderItem orderItem);
}
