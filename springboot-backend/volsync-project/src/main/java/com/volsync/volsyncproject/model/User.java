package com.volsync.volsyncproject.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.beans.ConstructorProperties;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String username;
    @Column(name = "pass_word")
    private String password;
    @Column(name = "user_role")
    private String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST)
    Volunteer volunteer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST)
    Organization organization;


}


