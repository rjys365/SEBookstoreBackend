package cn.rjys365.sebookstorebackend.dto;

import lombok.Getter;

@Getter
public class UserLoginResponse {
    private Long userId;
    private Long token;//TODO: real login
    private Integer role;
    public UserLoginResponse(Long userId, Long token, Integer role){
        this.userId = userId;
        this.token = token;
        this.role = role;
    }
}
