package cn.rjys365.sebookstorebackend.dao.daoimpl;

import cn.rjys365.sebookstorebackend.dao.OrderDAO;
import cn.rjys365.sebookstorebackend.entities.Book;
import cn.rjys365.sebookstorebackend.entities.Order;
import cn.rjys365.sebookstorebackend.repositories.BookRepository;
import cn.rjys365.sebookstorebackend.repositories.OrderRepository;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class OrderDAOImpl implements OrderDAO {

    private final OrderRepository orderRepository;

    private final BookRepository bookRepository;

    public OrderDAOImpl(OrderRepository orderRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Iterable<Order> getAllOrdersByUserId(Integer userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<Order> getOrderById(Integer id){
        return orderRepository.getOrderById(id);
    }

    @Override
    public Order saveOrder(Order order) {
        if(order.getItems()==null||order.getItems().isEmpty())throw new InvalidDataAccessApiUsageException("Empty Order");
        order.getItems().forEach(item->{
            if(item.getCount()==null||item.getCount()<=0)throw new InvalidDataAccessApiUsageException("Invalid order item count");
            item.setOrder(order);
            Optional<Book> bookOptional = this.bookRepository.findById(item.getId());
            if(bookOptional.isEmpty())throw new InvalidDataAccessApiUsageException("Non-existent order item");
            Book book = bookOptional.get();
            item.setBook(book);
        });
        //System.out.println(order);
        this.orderRepository.save(order);
        return order;
    }
}
