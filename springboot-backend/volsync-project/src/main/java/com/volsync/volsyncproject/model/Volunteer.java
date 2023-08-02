package com.volsync.volsyncproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;

import java.util.HashSet;
import java.util.Set;


/**
 * Class that defines a Volunteer entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "volunteers")
public class Volunteer {

    // primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // first name
    @Column(name = "first_name")
    private String firstName;

    // last name
    @Column(name = "last_name")
    private String lastName;

    // application message
    @Column(name = "application_message")
    private String applicationMessage;

    // one-to-one relation
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // many-to-many relation
    @WhereJoinTable(clause = "status = '0'")
    @ManyToMany
    //@Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "r_volunteer_post",
            joinColumns = @JoinColumn(name = "volunteer_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    @JsonIgnoreProperties({"signedUpVolunteers", "acceptedVolunteers", "rejectedVolunteers"})
    private Set<Post> signedUpPosts = new HashSet<>();

    // many-to-many relation
    @WhereJoinTable(clause = "status = '1'")
    @ManyToMany
    //@Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "r_volunteer_post",
            joinColumns = @JoinColumn(name = "volunteer_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    @JsonIgnoreProperties({"signedUpVolunteers", "acceptedVolunteers", "rejectedVolunteers"})
    private Set<Post> acceptedToPosts = new HashSet<>();

    // many-to-many relation
    @WhereJoinTable(clause = "status = '2'")
    @ManyToMany
    //@Fetch(FetchMode.JOIN)  // HOW the data is fetched
    @JoinTable(
            name = "r_volunteer_post",
            joinColumns = @JoinColumn(name = "volunteer_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    @JsonIgnoreProperties({"signedUpVolunteers", "acceptedVolunteers", "rejectedVolunteers"})
    private Set<Post> rejectedFromPosts = new HashSet<>();



}

