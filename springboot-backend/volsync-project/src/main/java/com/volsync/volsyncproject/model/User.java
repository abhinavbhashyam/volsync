package com.volsync.volsyncproject.model;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;


/**
 * Class that defines a User entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    // primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // username must be unique (we are using it for authentication)
    @Column(name = "user_name", unique = true)
    private String username;

    // password column (encrypted in the database using BCrypt)
    @Column(name = "pass_word")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // the role the user is (volunteer/organization)
    @Column(name = "user_role")
    private Role role;

    /*
    One-to-one with volunteer/organization (one user corresponds to one volunteer/organization entity)
     */
    // note: one of these may be null, since a user has one-to-one relation with EITHER a volunteer OR an organization
    @OneToOne(mappedBy = "user")
    Volunteer volunteer;

    @OneToOne(mappedBy = "user")
    Organization organization;

}


