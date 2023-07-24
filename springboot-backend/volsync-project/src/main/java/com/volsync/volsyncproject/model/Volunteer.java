package com.volsync.volsyncproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "volunteers")
public class Volunteer {

    // unique id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // first name
    @Column(name = "first_name")
    private String firstName;

    // last name
    @Column(name = "last_name")
    private String lastName;

    // gender
    private String gender;

    // application message
    @Column(name = "application_message")
    private String applicationMessage;

    // one-to-one relation
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


}

