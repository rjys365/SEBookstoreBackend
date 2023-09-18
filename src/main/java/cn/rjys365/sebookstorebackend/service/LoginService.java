package cn.rjys365.sebookstorebackend.service;

import cn.rjys365.sebookstorebackend.dto.LoginTimerDTO;

public interface LoginService {
    LoginTimerDTO getLoginInfo();
    LoginTimerDTO loginWithUsernameAndPassword(String username,String password);
    LoginTimerDTO logout();
}
