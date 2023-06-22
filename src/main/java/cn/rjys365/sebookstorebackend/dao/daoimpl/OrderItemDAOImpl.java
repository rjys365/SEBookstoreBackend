package cn.rjys365.sebookstorebackend.dao.daoimpl;

import cn.rjys365.sebookstorebackend.dao.OrderItemDAO;
import cn.rjys365.sebookstorebackend.dto.TopBookDTO;
import cn.rjys365.sebookstorebackend.repositories.OrderItemRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderItemDAOImpl implements OrderItemDAO {
    private final OrderItemRepository orderItemRepository;

    public OrderItemDAOImpl(OrderItemRepository orderItemRepository){
        this.orderItemRepository=orderItemRepository;
    }

    @Override
    public List<TopBookDTO> findTopBooksByTotalCountBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return this.orderItemRepository.findTopBooksByTotalCount(startDate,endDate);
    }

    @Override
    public List<TopBookDTO> findTopBooksByUserIdAndTotalCountBetweenDates(LocalDateTime startDate, LocalDateTime endDate, Integer userId) {
        return this.orderItemRepository.findTopBooksByTotalCountAndUserId(startDate, endDate,userId);
    }
}
