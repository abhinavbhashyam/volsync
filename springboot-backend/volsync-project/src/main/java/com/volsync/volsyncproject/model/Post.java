package com.volsync.volsyncproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 * Class that defines a post entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    // primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_body")
    private String postBody;

    @Column(name = "post_date")
    private Date postDate;

    @Column(name = "num_accepted")
    private int numAccepted;

    @Column(name = "num_limit")
    private int numLimit;

    @Transient
    private String postDateString;

    // one-to-many with organizations
    @ManyToOne

    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;
}
