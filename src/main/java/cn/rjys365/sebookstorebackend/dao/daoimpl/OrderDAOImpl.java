package cn.rjys365.sebookstorebackend.dao.daoimpl;

import cn.rjys365.sebookstorebackend.dao.OrderDAO;
import cn.rjys365.sebookstorebackend.entities.Order;
import cn.rjys365.sebookstorebackend.repositories.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderDAOImpl implements OrderDAO {

    private final OrderRepository orderRepository;

    public OrderDAOImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrdersByUserId(Integer userId) {
        return orderRepository.findAllByUserIdOrderByCreatedTimeDesc(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return this.orderRepository.findAllByOrderByCreatedTimeDesc();
    }

    @Override
    public Optional<Order> getOrderById(Integer id){
        return orderRepository.getOrderById(id);
    }

    @Override
    public Order saveOrder(Order order) {
        //System.out.println(order);
        this.orderRepository.save(order);
        return order;
    }
}
