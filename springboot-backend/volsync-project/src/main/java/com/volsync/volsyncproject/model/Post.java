package com.volsync.volsyncproject.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date postDate;

    @Column(name = "num_limit")
    private int numLimit;

    // one-to-many with organizations
    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    @JsonIgnoreProperties("postedPosts")
    private Organization postedByOrganization;

    // many-to-many with volunteers
    @ManyToMany(mappedBy = "signedUpPosts")
    @WhereJoinTable(clause = "status = '0'")
    //@Fetch(FetchMode.JOIN)
    @JsonIgnoreProperties({"signedUpPosts", "acceptedToPosts", "rejectedFromPosts"})
    private Set<Volunteer> signedUpVolunteers = new HashSet<>();

    @ManyToMany(mappedBy = "acceptedToPosts")
    @WhereJoinTable(clause = "status = '1'")
    //@Fetch(FetchMode.JOIN)
    @JsonIgnoreProperties({"signedUpPosts", "acceptedToPosts", "rejectedFromPosts"})
    private Set<Volunteer> acceptedVolunteers = new HashSet<>();

    @ManyToMany(mappedBy = "rejectedFromPosts")
    @WhereJoinTable(clause = "status = '2'")
    //@Fetch(FetchMode.JOIN)
    @JsonIgnoreProperties({"signedUpPosts", "acceptedToPosts", "rejectedFromPosts"})
    private Set<Volunteer> rejectedVolunteers = new HashSet<>();




}
