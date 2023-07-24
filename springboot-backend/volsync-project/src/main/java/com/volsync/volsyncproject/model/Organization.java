package com.volsync.volsyncproject.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "organizations")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "created_date")
    private Date createdDate;
}
