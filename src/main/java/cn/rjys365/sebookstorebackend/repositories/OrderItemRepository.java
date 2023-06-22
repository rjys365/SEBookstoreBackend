package cn.rjys365.sebookstorebackend.repositories;

import cn.rjys365.sebookstorebackend.dto.TopBookDTO;
import cn.rjys365.sebookstorebackend.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("SELECT new cn.rjys365.sebookstorebackend.dto.TopBookDTO(oi.bookId, oi.title, SUM(oi.count)) " +
            "FROM OrderItem oi " +
            "JOIN oi.order o " +
            "WHERE o.createdTime BETWEEN :startDate AND :endDate " +
            "GROUP BY oi.bookId, oi.title " +
            "ORDER BY SUM(oi.count) DESC")
    List<TopBookDTO> findTopBooksByTotalCount(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT new cn.rjys365.sebookstorebackend.dto.TopBookDTO(oi.bookId, oi.title, SUM(oi.count)) " +
            "FROM OrderItem oi " +
            "JOIN oi.order o " +
            "WHERE o.createdTime BETWEEN :startDate AND :endDate " +
            "AND o.userId = :userId " +
            "GROUP BY oi.bookId, oi.title " +
            "ORDER BY SUM(oi.count) DESC")
    List<TopBookDTO> findTopBooksByTotalCountAndUserId(LocalDateTime startDate, LocalDateTime endDate,Integer userId);
}
