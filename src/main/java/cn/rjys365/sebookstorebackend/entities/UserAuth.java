package cn.rjys365.sebookstorebackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users_auth")
public class UserAuth {
    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "passwd")
    private String password;

    @Column(name = "role")
    private Integer role;
}
