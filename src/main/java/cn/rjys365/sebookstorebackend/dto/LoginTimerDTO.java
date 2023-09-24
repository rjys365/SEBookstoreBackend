package cn.rjys365.sebookstorebackend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LoginTimerDTO {
    Long userId;
    UUID uuid;
    Long millisSinceLogin;
    Integer role;
    public LoginTimerDTO(){}
    public LoginTimerDTO(Long userId, UUID uuid, Long millisSinceLogin, Integer role) {
        this.userId = userId;
        this.uuid = uuid;
        this.millisSinceLogin = millisSinceLogin;
        this.role=role;
    }
}
