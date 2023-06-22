package cn.rjys365.sebookstorebackend.repositories;

import cn.rjys365.sebookstorebackend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findAllByUserId(Integer userId);

    List<Order> findAllByUserIdOrderByCreatedTimeDesc(Integer userId);

    List<Order> findAllByOrderByCreatedTimeDesc();

    Optional<Order> getOrderById(Integer id);

}
