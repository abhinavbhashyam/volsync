package com.volsync.volsyncproject.model;


import jakarta.persistence.*;
import lombok.Generated;

import java.beans.ConstructorProperties;

@Entity
@Table(name = "users")
public class User<UserType> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name", unique = true)
    private String userName;
    @Column(name = "pass_word")
    private String password;
    @Column(name = "user_role")
    private Role role;

}

enum Role {
    VOLUNTEER,
    ORGANIZATION
}
