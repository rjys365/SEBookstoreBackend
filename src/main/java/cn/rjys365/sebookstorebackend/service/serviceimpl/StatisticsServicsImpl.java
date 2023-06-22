package cn.rjys365.sebookstorebackend.service.serviceimpl;

import cn.rjys365.sebookstorebackend.dao.OrderItemDAO;
import cn.rjys365.sebookstorebackend.dto.TopBookDTO;
import cn.rjys365.sebookstorebackend.service.StatisticsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatisticsServicsImpl implements StatisticsService {
    private final OrderItemDAO orderItemDAO;

    public StatisticsServicsImpl(OrderItemDAO orderItemDAO){
        this.orderItemDAO=orderItemDAO;
    }


    @Override
    public List<TopBookDTO> findTopBooksByTotalCountBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return this.orderItemDAO.findTopBooksByTotalCountBetweenDates(startDate, endDate);
    }

    @Override
    public List<TopBookDTO> findTopBooksByUserIdAndTotalCountBetweenDates(LocalDateTime startDate, LocalDateTime endDate, Integer userId) {
        return this.orderItemDAO.findTopBooksByUserIdAndTotalCountBetweenDates(startDate, endDate,userId);
    }
}
