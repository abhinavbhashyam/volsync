package com.volsync.volsyncproject.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;

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
    private Date postDate;

    @Column(name = "num_limit")
    private int numLimit;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    private String postDateString;

    // one-to-many with organizations
    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    @JsonIgnoreProperties("postedPosts")
    private Organization postedByOrganization;

    // many-to-many with volunteers
    @ManyToMany(mappedBy = "signedUpPosts")
    @WhereJoinTable(clause = "status = 'pending'")
    @JsonIgnoreProperties({"signedUpPosts", "acceptedToPosts", "rejectedFromPosts"})
    private Set<Volunteer> signedUpVolunteers = new HashSet<>();

    @ManyToMany(mappedBy = "acceptedToPosts")
    @WhereJoinTable(clause = "status = 'accepted'")
    @JsonIgnoreProperties({"signedUpPosts", "acceptedToPosts", "rejectedFromPosts"})
    private Set<Volunteer> acceptedVolunteers = new HashSet<>();

    @ManyToMany(mappedBy = "rejectedFromPosts")
    @WhereJoinTable(clause = "status = 'rejected'")
    @JsonIgnoreProperties({"signedUpPosts", "acceptedToPosts", "rejectedFromPosts"})
    private Set<Volunteer> rejectedVolunteers = new HashSet<>();




}
