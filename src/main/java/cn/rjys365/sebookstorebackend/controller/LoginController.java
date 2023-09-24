package cn.rjys365.sebookstorebackend.controller;

import cn.rjys365.sebookstorebackend.dto.LoginTimerDTO;
import cn.rjys365.sebookstorebackend.service.LoginService;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/")
    public ResponseEntity<LoginTimerDTO> loginWithUsernameAndPassword(@RequestParam String username, @RequestParam String password){
        var dto= loginService.loginWithUsernameAndPassword(username,password);
        if(dto==null)return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/")
    public ResponseEntity<LoginTimerDTO> getLoginInfo(){
        var dto= loginService.getLoginInfo();
        if(dto==null)return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/")
    public ResponseEntity<LoginTimerDTO> logout(){
        var dto= loginService.logout();
        if(dto==null)return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(dto);
    }
}
