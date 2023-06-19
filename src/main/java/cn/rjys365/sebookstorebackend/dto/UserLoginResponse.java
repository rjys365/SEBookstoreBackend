package cn.rjys365.sebookstorebackend.dto;

import lombok.Getter;

@Getter
public class UserLoginResponse {
    private Integer userId;
    private Integer token;//TODO: real login
    private Integer role;
    public UserLoginResponse(Integer userId, Integer token, Integer role){
        this.userId = userId;
        this.token = token;
        this.role = role;
    }
}
