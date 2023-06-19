package cn.rjys365.sebookstorebackend.service.serviceimpl;

import cn.rjys365.sebookstorebackend.dao.UserDAO;
import cn.rjys365.sebookstorebackend.dto.UserLoginResponse;
import cn.rjys365.sebookstorebackend.dto.UserRegisterRequest;
import cn.rjys365.sebookstorebackend.entities.User;
import cn.rjys365.sebookstorebackend.entities.UserAuth;
import cn.rjys365.sebookstorebackend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<UserLoginResponse> loginByUsername(String username, String password) {
        Optional<User> userOptional=userDAO.findUserByName(username);
        if(userOptional.isPresent()){
            User user=userOptional.get();
            if(user.getUserAuth().getPassword().equals(password)&&user.getUserAuth().getRole()!=0){
                UserLoginResponse userLoginResponse =new UserLoginResponse(user.getId(),user.getId(),user.getUserAuth().getRole());
                return Optional.of(userLoginResponse);
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

    @Override
    public Optional<User> addUser(UserRegisterRequest user) {
        if(user==null)return Optional.empty();
        User userToAdd=new User();
        userToAdd.setName(user.getName());
        userToAdd.setEmail(user.getEmail());
        userToAdd.setUserAuth(new UserAuth());
        userToAdd.getUserAuth().setUser(userToAdd);
        userToAdd.getUserAuth().setPassword(user.getPassword());
        return userDAO.addUser(userToAdd);
    }
}
