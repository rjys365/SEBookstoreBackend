package cn.rjys365.sebookstorebackend.service;

import cn.rjys365.sebookstorebackend.entities.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    public List<Order> getAllOrdersByUserId(Integer userId);

    public List<Order> getAllOrders();

    public Optional<Order> getOrderById(Integer id);

    public Optional<Order> getOrderByUuid(UUID uuid);

    public Order saveOrder(Order order);

    public Optional<Order> createOrderFromUserCartItems(Integer userId, UUID uuid);

    public Optional<Order> createOrderFromItem(Integer userId, Integer bookId, Integer quantity, UUID uuid);

}
