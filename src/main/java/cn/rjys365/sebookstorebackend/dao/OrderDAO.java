package cn.rjys365.sebookstorebackend.dao;

import cn.rjys365.sebookstorebackend.entities.Order;

import java.util.ArrayList;
import java.util.Optional;

public interface OrderDAO {
    public Iterable<Order> getAllOrdersByUserId(Integer userId);

    public Optional<Order> getOrderById(Integer id);

    public Order saveOrder(Order order);
}
