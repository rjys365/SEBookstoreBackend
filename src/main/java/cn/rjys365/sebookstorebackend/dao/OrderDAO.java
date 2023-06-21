package cn.rjys365.sebookstorebackend.dao;

import cn.rjys365.sebookstorebackend.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDAO {
    public List<Order> getAllOrdersByUserId(Integer userId);

    public List<Order> getAllOrders();

    public Optional<Order> getOrderById(Integer id);

    public Order saveOrder(Order order);
}
