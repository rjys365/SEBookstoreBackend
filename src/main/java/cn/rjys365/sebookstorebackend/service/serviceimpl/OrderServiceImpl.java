package cn.rjys365.sebookstorebackend.service.serviceimpl;

import cn.rjys365.sebookstorebackend.dao.BookDAO;
import cn.rjys365.sebookstorebackend.dao.OrderDAO;
import cn.rjys365.sebookstorebackend.dao.UserDAO;
import cn.rjys365.sebookstorebackend.entities.*;
import cn.rjys365.sebookstorebackend.exception.OrderServiceException;
import cn.rjys365.sebookstorebackend.service.OrderService;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    private final UserDAO userDAO;

    private final BookDAO bookDAO;

    public OrderServiceImpl(OrderDAO orderDAO, UserDAO userDAO, BookDAO bookDAO) {
        this.orderDAO = orderDAO;
        this.userDAO = userDAO;
        this.bookDAO = bookDAO;
    }

    @Override
    public List<Order> getAllOrdersByUserId(Integer userId) {
        return this.orderDAO.getAllOrdersByUserId(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return this.orderDAO.getAllOrders();
    }

    @Override
    public Optional<Order> getOrderById(Integer id) {
        return this.orderDAO.getOrderById(id);
    }

    @Override
    public Order saveOrder(Order order) {
        try {
            if(order.getItems()==null||order.getItems().isEmpty())throw new InvalidDataAccessApiUsageException("Empty Order");
            order.getItems().forEach(item->{
                if(item.getCount()==null||item.getCount()<=0)throw new InvalidDataAccessApiUsageException("Invalid order item count");
                item.setOrder(order);
                Optional<Book> bookOptional = this.bookDAO.getBookById(item.getBookId());
                if(bookOptional.isEmpty())throw new InvalidDataAccessApiUsageException("Non-existent order item");
                Book book = bookOptional.get();
                if(book.getStock()<item.getCount())throw new OrderServiceException("Out of stock");
                item.setBookIdPriceTitle(book);
            });
            order.setCreatedTime(LocalDateTime.now());
            Order ret = this.orderDAO.saveOrder(order);
            order.getItems().forEach(item->{
                Optional<Book> bookOptional = this.bookDAO.getBookById(item.getBookId());
                if(bookOptional.isEmpty())throw new InvalidDataAccessApiUsageException("Non-existent order item");
                Book book = bookOptional.get();
                book.setStock(book.getStock()-item.getCount());
                bookDAO.saveBook(book);
            });
            return ret;
        } catch (DataAccessException e) {
            throw new OrderServiceException(e.getMessage());
        }
    }

    @Override
    public Optional<Order> createOrderFromUserCartItems(Integer userId) {
        Optional<User> userOptional = userDAO.findUserById(userId);
        if(userOptional.isEmpty())return Optional.empty();
        User user=userOptional.get();
        var cartItems=user.getCartItems();
        if(cartItems.isEmpty())return Optional.empty();
        Order order=new Order();
        order.setUserId(userId);
        LinkedHashSet<OrderItem> orderItems=new LinkedHashSet<>();
        for(CartItem cartItem:cartItems){
            Book book=cartItem.getBook();
            if(book==null)return Optional.empty();
            OrderItem orderItem=new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBookId(book.getId());
            orderItem.setTitle(book.getTitle());
            orderItem.setPrice(book.getPrice());
            orderItem.setCount(cartItem.getQuantity());
            orderItems.add(orderItem);
        }
        order.setItems(orderItems);
        try {
            return Optional.of(this.saveOrder(order));
        }
        catch (OrderServiceException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Order> createOrderFromItem(Integer userId, Integer bookId, Integer quantity) {
        Optional<User> userOptional = userDAO.findUserById(userId);
        if(userOptional.isEmpty())return Optional.empty();
        User user=userOptional.get();
        Optional<Book> bookOptional=bookDAO.getBookById(bookId);
        if(bookOptional.isEmpty())return Optional.empty();
        Book book=bookOptional.get();
        Order order=new Order();
        order.setUserId(userId);
        LinkedHashSet<OrderItem> orderItems=new LinkedHashSet<>();
        OrderItem orderItem=new OrderItem();
        orderItem.setOrder(order);
        orderItem.setBookId(book.getId());
        orderItem.setTitle(book.getTitle());
        orderItem.setPrice(book.getPrice());
        orderItem.setCount(quantity);
        orderItems.add(orderItem);
        order.setItems(orderItems);
        try {
            return Optional.of(this.saveOrder(order));
        }
        catch (OrderServiceException e){
            return Optional.empty();
        }
    }
}
