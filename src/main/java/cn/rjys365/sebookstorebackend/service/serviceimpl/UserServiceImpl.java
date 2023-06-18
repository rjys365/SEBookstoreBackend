package cn.rjys365.sebookstorebackend.service.serviceimpl;

import cn.rjys365.sebookstorebackend.dao.UserDAO;
import cn.rjys365.sebookstorebackend.dto.UserLogin;
import cn.rjys365.sebookstorebackend.entities.User;
import cn.rjys365.sebookstorebackend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<UserLogin> loginByUsername(String username, String password) {
        Optional<User> userOptional=userDAO.findUserByName(username);
        if(userOptional.isPresent()){
            User user=userOptional.get();
            if(user.getUserAuth().getPassword().equals(password)&&user.getUserAuth().getRole()!=0){
                UserLogin userLogin=new UserLogin(user.getId(),user.getId(),user.getUserAuth().getRole());
                return Optional.of(userLogin);
            }
        }
        return Optional.empty();
    }

    @Override
    public User blockUser(Integer id,Integer blockerId) {
        Optional<User> userOptional=userDAO.findUserById(id);
        Optional<User> blockerOptional=userDAO.findUserById(blockerId);
        if(blockerOptional.isEmpty())return null;
        if(blockerOptional.get().getUserAuth().getRole()!=2)return null;
        if(userOptional.isPresent()){
            User user=userOptional.get();
            user.getUserAuth().setRole(0);
            return userDAO.saveUser(user);
        }
        return null;
    }

    @Override
    public User unBlockUser(Integer id,Integer unBlockerId) {
        Optional<User> userOptional=userDAO.findUserById(id);
        if(userOptional.isPresent()){
            User user=userOptional.get();
            user.getUserAuth().setRole(1);
            return userDAO.saveUser(user);
        }
        return null;
    }

    @Override
    public Iterable<User> getAllUsers(Integer getterId) {
        Optional<User> userOptional=userDAO.findUserById(getterId);
        if(userOptional.isEmpty())return null;
        if(userOptional.get().getUserAuth().getRole()!=2)return null;
        return userDAO.findAll();
    }
}
