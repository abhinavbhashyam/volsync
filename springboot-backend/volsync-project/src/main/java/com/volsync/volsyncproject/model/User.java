package com.volsync.volsyncproject.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;



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

    @Column(name = "user_name", unique = true)
    private String username;
    @Column(name = "pass_word")
    private String password;
    @Column(name = "user_role")
    private String role;

    // note: one of these may be null, since a user has one-to-one relation with EITHER a volunteer OR an organization
    // I found it would be easier to just store references to both in a single User to avoid extra boilerplate code
    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Volunteer volunteer;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Organization organization;


}


