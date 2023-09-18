package cn.rjys365.sebookstorebackend.service.serviceimpl;

import cn.rjys365.sebookstorebackend.dao.UserDAO;
import cn.rjys365.sebookstorebackend.dto.LoginTimerDTO;
import cn.rjys365.sebookstorebackend.entities.User;
import cn.rjys365.sebookstorebackend.service.LoginService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class LoginServiceImpl implements LoginService {
    private final UserDAO userDAO;
    private User user = null;
    private UUID uuid = null;
    private ZonedDateTime loginTime = null;

    public LoginServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public LoginTimerDTO getLoginInfo() {
        return null;
    }

    @Override
    public LoginTimerDTO loginWithUsernameAndPassword(String username, String password) {
        return null;
    }

    @Override
    public LoginTimerDTO logout() {
        return null;
    }
}
