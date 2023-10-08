package cn.rjys365.sebookstorebackend.service.serviceimpl;

import cn.rjys365.sebookstorebackend.dao.BookDAO;
import cn.rjys365.sebookstorebackend.dao.OrderDAO;
import cn.rjys365.sebookstorebackend.dao.OrderItemDAO;
import cn.rjys365.sebookstorebackend.dao.UserDAO;
import cn.rjys365.sebookstorebackend.entities.*;
import cn.rjys365.sebookstorebackend.exception.OrderServiceException;
import cn.rjys365.sebookstorebackend.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    private final UserDAO userDAO;

    private final BookDAO bookDAO;

    private final OrderItemDAO orderItemDAO;

    public OrderServiceImpl(OrderDAO orderDAO, UserDAO userDAO, BookDAO bookDAO, OrderItemDAO orderItemDAO) {
        this.orderDAO = orderDAO;
        this.userDAO = userDAO;
        this.bookDAO = bookDAO;
        this.orderItemDAO = orderItemDAO;
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
    public Optional<Order> getOrderByUuid(UUID uuid) {
        return orderDAO.getOrderByUuid(uuid);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED)
    public Order saveOrder(Order order) {
        try {
            validateOrder(order);
            order.setCreatedTime(LocalDateTime.now());
            this.orderDAO.saveOrder(order);
            saveOrderItems(order);
            updateStock(order);
            return order;
        } catch (DataAccessException e) {
            throw new OrderServiceException(e.getMessage());
        }
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public void saveOrderItems(Order order){
        order.getItems().forEach(orderItemDAO::saveOrderItem);
    }
    @Transactional(value = Transactional.TxType.SUPPORTS)
    public void updateStock(Order order) {
        order.getItems().forEach(item->{
            Optional<Book> bookOptional = this.bookDAO.getBookById(item.getBookId());
            if(bookOptional.isEmpty())throw new InvalidDataAccessApiUsageException("Non-existent order item");
            Book book = bookOptional.get();
            book.setStock(book.getStock()-item.getCount());
            bookDAO.saveBook(book);
        });
    }

    @Transactional(value = Transactional.TxType.SUPPORTS)
    public void validateOrder(Order order) {
        if(order.getItems()==null|| order.getItems().isEmpty())throw new InvalidDataAccessApiUsageException("Empty Order");
        order.getItems().forEach(item->{
            if(item.getCount()==null||item.getCount()<=0)throw new InvalidDataAccessApiUsageException("Invalid order item count");
            item.setOrder(order);
            Optional<Book> bookOptional = this.bookDAO.getBookById(item.getBookId());
            if(bookOptional.isEmpty())throw new InvalidDataAccessApiUsageException("Non-existent order item");
            Book book = bookOptional.get();
            if(book.getStock()<item.getCount())throw new OrderServiceException("Out of stock");
            item.setBookIdPriceTitle(book);
        });
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED)
    public Optional<Order> createOrderFromUserCartItems(Long userId, UUID uuid) {
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
        order.setUuid(uuid);
        try {
            return Optional.of(this.saveOrder(order));
        }
        catch (OrderServiceException e){
            return Optional.empty();
        }
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED)
    public Optional<Order> createOrderFromItem(Long userId, Integer bookId, Integer quantity, UUID uuid) {
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
        order.setUuid(uuid);
        try {
            return Optional.of(this.saveOrder(order));
        }
        catch (OrderServiceException e){
            return Optional.empty();
        }
    }
}
