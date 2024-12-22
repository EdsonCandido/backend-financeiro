package com.zuko.crm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity(name = "tb_users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long userId;

    private String username;

    @Column(unique = true)
    private String login;

    @JsonIgnore
    private String password;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    private


}
