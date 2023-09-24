package cn.rjys365.sebookstorebackend.service.serviceimpl;

import cn.rjys365.sebookstorebackend.dao.UserDAO;
import cn.rjys365.sebookstorebackend.dto.LoginTimerDTO;
import cn.rjys365.sebookstorebackend.entities.User;
import cn.rjys365.sebookstorebackend.service.LoginService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class LoginServiceImpl implements LoginService {
    private final UserDAO userDAO;
    private User user = null;
    private UUID uuid = null;
    private ZonedDateTime loginTime = null;
    private Boolean loggedIn=false;

    public LoginServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public LoginTimerDTO getLoginInfo() {
        if(!loggedIn)return null;
        return new LoginTimerDTO(user.getId(),uuid,loginTime.toInstant().toEpochMilli(),user.getUserAuth().getRole() );
    }

    @Override
    public LoginTimerDTO loginWithUsernameAndPassword(String username, String password) {
        if(loggedIn)return null;
        var userOptional=userDAO.findUserByName(username);
        if(userOptional.isPresent()){
            var user=userOptional.get();
            if(user.getUserAuth().getPassword().equals(password)){
                this.user=user;
                this.uuid=UUID.randomUUID();
                this.loginTime=ZonedDateTime.now();
                this.loggedIn=true;
                return new LoginTimerDTO(user.getId(),uuid, 0L,user.getUserAuth().getRole() );
            }
        }
        return null;
    }

    @Override
    public LoginTimerDTO logout() {
        if(!loggedIn)return null;
        loggedIn=false;
        return new LoginTimerDTO(user.getId(),uuid, Duration.between(loginTime,ZonedDateTime.now()).toMillis(),null );
    }
}
