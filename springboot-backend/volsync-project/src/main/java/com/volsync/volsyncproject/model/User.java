package com.volsync.volsyncproject.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonMerge;
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
    private String password;

    // the role the user is (volunteer/organization)
    @Column(name = "user_role")
    private String role;

    // note: one of these may be null, since a user has one-to-one relation with EITHER a volunteer OR an organization
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Volunteer volunteer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Organization organization;


}


