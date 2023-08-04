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
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Class that defines an organization entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "organizations")
@Transactional
public class Organization {
    // primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // org_name column
    @Column(name = "org_name")
    private String orgName;

    // one-to-one relation with user
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // one-to-many relation (one organization has many posts)
    @JsonIgnoreProperties("postedByOrganization")
    @OneToMany(mappedBy = "postedByOrganization")
    @Fetch(FetchMode.JOIN)
    private Set<Post> postedPosts;
}
