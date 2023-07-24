package com.volsync.volsyncproject.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "volunteers")
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    private Sex sex;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "application_message")
    private String applicationMessage;
}

enum Sex {
    M,
    F
}
