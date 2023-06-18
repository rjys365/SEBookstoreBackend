package cn.rjys365.sebookstorebackend.dao.daoimpl;

import cn.rjys365.sebookstorebackend.dao.UserDAO;
import cn.rjys365.sebookstorebackend.entities.User;
import cn.rjys365.sebookstorebackend.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {
    private UserRepository userRepository;

    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUserById(Integer id) {
        return this.userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return this.userRepository.findByName(name);
    }

    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public Iterable<User> findAll() {
        return this.userRepository.findAll();
    }
}
