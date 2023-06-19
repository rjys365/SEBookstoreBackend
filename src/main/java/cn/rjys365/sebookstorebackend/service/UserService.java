package cn.rjys365.sebookstorebackend.service;

import cn.rjys365.sebookstorebackend.dto.UserLoginResponse;
import cn.rjys365.sebookstorebackend.dto.UserRegisterRequest;
import cn.rjys365.sebookstorebackend.entities.User;

import java.util.Optional;

public interface UserService {
    public Optional<UserLoginResponse> loginByUsername(String username, String password);

    public User blockUser(Integer id,Integer blockerId);

    public User unBlockUser(Integer id,Integer unBlockerId);

    public Iterable<User> getAllUsers(Integer getterId);

    public Optional<User> addUser(UserRegisterRequest user);
}
