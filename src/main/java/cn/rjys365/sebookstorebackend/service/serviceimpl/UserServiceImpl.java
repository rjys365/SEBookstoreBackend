package cn.rjys365.sebookstorebackend.service.serviceimpl;

import cn.rjys365.sebookstorebackend.dao.BookDAO;
import cn.rjys365.sebookstorebackend.dao.UserDAO;
import cn.rjys365.sebookstorebackend.dto.UserLoginResponse;
import cn.rjys365.sebookstorebackend.dto.UserRegisterRequest;
import cn.rjys365.sebookstorebackend.entities.Book;
import cn.rjys365.sebookstorebackend.entities.CartItem;
import cn.rjys365.sebookstorebackend.entities.User;
import cn.rjys365.sebookstorebackend.entities.UserAuth;
import cn.rjys365.sebookstorebackend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    private final BookDAO bookDAO;

    public UserServiceImpl(UserDAO userDAO, BookDAO bookDAO) {
        this.userDAO = userDAO;
        this.bookDAO = bookDAO;
    }

    @Override
    public Optional<UserLoginResponse> loginByUsername(String username, String password) {
        Optional<User> userOptional = userDAO.findUserByName(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getUserAuth().getPassword().equals(password) && user.getUserAuth().getRole() != 0) {
                UserLoginResponse userLoginResponse = new UserLoginResponse(user.getId(), user.getId(), user.getUserAuth().getRole());
                return Optional.of(userLoginResponse);
            }
        }
        return Optional.empty();
    }

    @Override
    public User blockUser(Integer id, Integer blockerId) {
        Optional<User> userOptional = userDAO.findUserById(id);
        Optional<User> blockerOptional = userDAO.findUserById(blockerId);
        if (blockerOptional.isEmpty()) return null;
        if (blockerOptional.get().getUserAuth().getRole() != 2) return null;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.getUserAuth().setRole(0);
            return userDAO.saveUser(user);
        }
        return null;
    }

    @Override
    public User unBlockUser(Integer id, Integer unBlockerId) {
        Optional<User> userOptional = userDAO.findUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.getUserAuth().setRole(1);
            return userDAO.saveUser(user);
        }
        return null;
    }

    @Override
    public Iterable<User> getAllUsers(Integer getterId) {
        Optional<User> userOptional = userDAO.findUserById(getterId);
        if (userOptional.isEmpty()) return null;
        if (userOptional.get().getUserAuth().getRole() != 2) return null;
        return userDAO.findAll();
    }

    @Override
    public Optional<User> addUser(UserRegisterRequest user) {
        if (user == null) return Optional.empty();
        User userToAdd = new User();
        userToAdd.setName(user.getName());
        userToAdd.setEmail(user.getEmail());
        userToAdd.setUserAuth(new UserAuth());
        userToAdd.getUserAuth().setUser(userToAdd);
        userToAdd.getUserAuth().setPassword(user.getPassword());
        return userDAO.addUser(userToAdd);
    }

    @Override
    public Optional<List<CartItem>> getUserCartItems(Integer userId) {
        Optional<User> userOptional = userDAO.findUserById(userId);
        if (userOptional.isEmpty()) return Optional.empty();
        User user = userOptional.get();
        return Optional.of(user.getCartItems());
    }

    @Override
    public Optional<List<CartItem>> setUserCartItems(Integer userId, List<CartItem> cart) {
        Optional<User> userOptional = userDAO.findUserById(userId);
        if (userOptional.isEmpty()) return Optional.empty();
        User user = userOptional.get();
        user.getCartItems().clear();
        cart.forEach(item -> item.setUser(user));
        user.getCartItems().addAll(cart);
        return Optional.of(userDAO.saveUser(user).getCartItems());
    }

    @Override
    public Optional<List<CartItem>> addUserCartItem(Integer userId, Integer bookId, Integer count) {
        Optional<User> userOptional = userDAO.findUserById(userId);
        Optional<Book> bookOptional = bookDAO.getBookById(bookId);
        if (userOptional.isEmpty() || bookOptional.isEmpty()) return Optional.empty();
        User user = userOptional.get();
        Book book = bookOptional.get();
        List<CartItem> cartItems = user.getCartItems();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getBook().equals(book)) {
                int newQuantity = cartItem.getQuantity() + count;
                if (newQuantity < 0) return Optional.empty();
                if (newQuantity == 0) {
                    cartItems.remove(cartItem);
                } else {
                    cartItem.setQuantity(newQuantity);
                }
                user.setCartItems(cartItems);
                return Optional.of(userDAO.saveUser(user).getCartItems());
            }
        }
        if (count <= 0) return Optional.empty();
        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setQuantity(count);
        cartItem.setUser(user);
        cartItems.add(cartItem);
        user.setCartItems(cartItems);
        return Optional.of(userDAO.saveUser(user).getCartItems());
    }

    @Override
    public Optional<List<CartItem>> setUserCartItem(Integer userId, Integer bookId, Integer count) {
        if (count < 0) return Optional.empty();
        Optional<User> userOptional = userDAO.findUserById(userId);
        Optional<Book> bookOptional = bookDAO.getBookById(bookId);
        if (userOptional.isEmpty() || bookOptional.isEmpty()) return Optional.empty();
        User user = userOptional.get();
        Book book = bookOptional.get();
        List<CartItem> cartItems = user.getCartItems();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getBook().equals(book)) {
                if (count == 0) cartItems.remove(cartItem);
                else cartItem.setQuantity(count);
                user.setCartItems(cartItems);
                return Optional.of(userDAO.saveUser(user).getCartItems());
            }
        }
        if (count == 0) return Optional.empty();
        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setQuantity(count);
        cartItem.setUser(user);
        cartItems.add(cartItem);
        user.setCartItems(cartItems);
        return Optional.of(userDAO.saveUser(user).getCartItems());
    }
}
