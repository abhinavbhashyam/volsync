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

    // post title column
    @Column(name = "post_title")
    private String postTitle;

    // post body column
    @Column(name = "post_body")
    private String postBody;

    // post date column
    @Column(name = "post_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date postDate;

    // limit of the number of volunteers that can be accepted to this post
    @Column(name = "num_limit")
    private int numLimit;

    // many-to-many with organizations (many posts belong to one organization)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "postedPosts"})
    private Organization postedByOrganization;

    /*
     Following three sets support many-to-many relation with volunteers (many posts can have many volunteers)
     */
    @ManyToMany(mappedBy = "signedUpPosts")
    @WhereJoinTable(clause = "status = '0'")    // only want signed up posts
    @JsonIgnoreProperties({"signedUpPosts", "acceptedToPosts", "rejectedFromPosts"})
    private Set<Volunteer> signedUpVolunteers = new HashSet<>();

    @ManyToMany(mappedBy = "acceptedToPosts")
    @WhereJoinTable(clause = "status = '1'")    // only want accepted posts
    @JsonIgnoreProperties({"signedUpPosts", "acceptedToPosts", "rejectedFromPosts"})
    private Set<Volunteer> acceptedVolunteers = new HashSet<>();

    @ManyToMany(mappedBy = "rejectedFromPosts")
    @WhereJoinTable(clause = "status = '2'")    // only want rejected posts
    @JsonIgnoreProperties({"signedUpPosts", "acceptedToPosts", "rejectedFromPosts"})
    private Set<Volunteer> rejectedVolunteers = new HashSet<>();




}
