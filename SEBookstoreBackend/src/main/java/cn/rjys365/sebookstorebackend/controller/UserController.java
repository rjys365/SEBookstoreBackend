package cn.rjys365.sebookstorebackend.controller;

import cn.rjys365.sebookstorebackend.dto.*;
import cn.rjys365.sebookstorebackend.entities.Book;
import cn.rjys365.sebookstorebackend.entities.CartItem;
import cn.rjys365.sebookstorebackend.entities.User;
import cn.rjys365.sebookstorebackend.service.BookService;
import cn.rjys365.sebookstorebackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/")
//@CrossOrigin("http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService, BookService bookService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public UserLoginResponse loginByUserNameAndPassword(@RequestParam String username, @RequestParam String password){
        Optional<UserLoginResponse> userLoginOptional = userService.loginByUsername(username, password);
        if(userLoginOptional.isPresent()){
            return userLoginOptional.get();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Illegal login");
    }

    @PostMapping("/{id}/block")
    public UserDigest blockUser(@PathVariable Long id,@RequestParam Long blockerId){
        User user = userService.blockUser(id,blockerId);
        if(user!=null){
            return new UserDigest(user);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Illegal block operation");
    }

    @PostMapping("/{id}/unblock")
    public UserDigest unBlockUser(@PathVariable Long id,@RequestParam Long unBlockerId){
        User user = userService.unBlockUser(id,unBlockerId);
        if(user!=null){
            return new UserDigest(user);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Illegal unblock operation");
    }

    @GetMapping("/allUsers")
    public ArrayList<UserDigest> getAllUsersDigest(@RequestParam Long getterId){
        Iterable<User> users=this.userService.getAllUsers(getterId);
        if(users==null)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Illegal all user operation");
        ArrayList<UserDigest> digests=new ArrayList<>();
        users.forEach(user -> {
            digests.add(new UserDigest(user));
        });
        return digests;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public UserLoginResponse addUser(@RequestBody UserRegisterRequest userRegisterRequest){
        Optional<User> userOptional = userService.addUser(userRegisterRequest);
        if(userOptional.isEmpty())throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Illegal register user operation");
        var userLoginOptional=userService.loginByUsername(userRegisterRequest.getName(), userRegisterRequest.getPassword());
        if(userLoginOptional.isEmpty())throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Illegal register user operation");
        return userLoginOptional.get();
    }
}
