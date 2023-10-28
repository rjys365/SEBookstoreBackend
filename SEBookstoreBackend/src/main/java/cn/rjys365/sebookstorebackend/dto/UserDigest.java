// User digest for management purposes

package cn.rjys365.sebookstorebackend.dto;

import cn.rjys365.sebookstorebackend.entities.User;
import lombok.Getter;

@Getter
public class UserDigest {
    private Long id;
    private String name;
    private Integer role;

    public UserDigest(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.role = user.getUserAuth().getRole();
    }
}
