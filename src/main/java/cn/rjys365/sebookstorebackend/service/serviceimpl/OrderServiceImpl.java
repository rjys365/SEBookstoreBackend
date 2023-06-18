package cn.rjys365.sebookstorebackend.service.serviceimpl;

import cn.rjys365.sebookstorebackend.dao.OrderDAO;
import cn.rjys365.sebookstorebackend.entities.Order;
import cn.rjys365.sebookstorebackend.exception.OrderServiceException;
import cn.rjys365.sebookstorebackend.service.OrderService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public Iterable<Order> getAllOrdersByUserId(Integer userId) {
        return this.orderDAO.getAllOrdersByUserId(userId);
    }

    @Override
    public Optional<Order> getOrderById(Integer id) {
        return this.orderDAO.getOrderById(id);
    }

    @Override
    public Order saveOrder(Order order) {
        try{
            return this.orderDAO.saveOrder(order);
        }
        catch(DataAccessException e){
            throw new OrderServiceException(e.getMessage());
        }
    }
}
