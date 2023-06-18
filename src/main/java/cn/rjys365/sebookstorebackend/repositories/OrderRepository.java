package cn.rjys365.sebookstorebackend.repositories;

import cn.rjys365.sebookstorebackend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    Iterable<Order> findAllByUserId(Integer userId);

    Optional<Order> getOrderById(Integer id);

}
