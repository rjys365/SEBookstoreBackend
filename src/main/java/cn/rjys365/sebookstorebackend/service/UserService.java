package cn.rjys365.sebookstorebackend.service;

import cn.rjys365.sebookstorebackend.dto.UserLoginResponse;
import cn.rjys365.sebookstorebackend.dto.UserRegisterRequest;
import cn.rjys365.sebookstorebackend.entities.CartItem;
import cn.rjys365.sebookstorebackend.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Optional<UserLoginResponse> loginByUsername(String username, String password);

    public User blockUser(Long id,Long blockerId);

    public User unBlockUser(Long id,Long unBlockerId);

    public Iterable<User> getAllUsers(Long getterId);

    public Optional<User> addUser(UserRegisterRequest user);

    public Optional<List<CartItem> > getUserCartItems(Long userId);

    public Optional<List<CartItem> > setUserCartItems(Long userId, List<CartItem> cart);

    public Optional<List<CartItem> > addUserCartItem(Long userId, Integer BookId, Integer count);

    public Optional<List<CartItem> > setUserCartItem(Long userId, Integer BookId, Integer count);
}
