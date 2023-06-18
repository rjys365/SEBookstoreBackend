package cn.rjys365.sebookstorebackend.service;

import cn.rjys365.sebookstorebackend.dto.UserLogin;
import cn.rjys365.sebookstorebackend.entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {
    public Optional<UserLogin> loginByUsername(String username, String password);

    public User blockUser(Integer id,Integer blockerId);

    public User unBlockUser(Integer id,Integer unBlockerId);

    public Iterable<User> getAllUsers(Integer getterId);
}
