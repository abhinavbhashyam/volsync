package com.volsync.volsyncproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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

    // one-to-one relation (one volunteer corresponds to one user)
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    /*
    Following three sets support many-to-many relation with volunteers (many volunteers can have many posts)
    */
    @WhereJoinTable(clause = "status = 'PENDING'")    // only want pending posts
    @ManyToMany
    @JoinTable(
            name = "r_volunteer_post",
            joinColumns = @JoinColumn(name = "volunteer_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    @JsonIgnoreProperties({"signedUpVolunteers", "acceptedVolunteers", "rejectedVolunteers"})
    @Fetch(FetchMode.JOIN)
    private Set<Post> signedUpPosts = new HashSet<>();

    @WhereJoinTable(clause = "status = 'ACCEPTED'")    // only want accepted posts
    @ManyToMany
    @JoinTable(
            name = "r_volunteer_post",
            joinColumns = @JoinColumn(name = "volunteer_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    @JsonIgnoreProperties({"signedUpVolunteers", "acceptedVolunteers", "rejectedVolunteers"})
    @Fetch(FetchMode.JOIN)
    private Set<Post> acceptedToPosts = new HashSet<>();

    @WhereJoinTable(clause = "status = 'REJECTED'")    // only want rejected posts
    @ManyToMany
    @JoinTable(
            name = "r_volunteer_post",
            joinColumns = @JoinColumn(name = "volunteer_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    @JsonIgnoreProperties({"signedUpVolunteers", "acceptedVolunteers", "rejectedVolunteers"})
    @Fetch(FetchMode.JOIN)
    private Set<Post> rejectedFromPosts = new HashSet<>();
    

}

