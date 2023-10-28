package cn.rjys365.sebookstorebackend.dao;

import cn.rjys365.sebookstorebackend.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserDAO {
    public Optional<User> findUserById(Long id);

    public Optional<User> findUserByName(String username);

    public User saveUser(User user);

    Iterable<User> findAll();

    Optional<User> addUser(User user);
}
