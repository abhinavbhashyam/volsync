package com.volsync.volsyncproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.volsync.volsyncproject.primary_key.VolunteerPostId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity corresponding to our volunteer_post join table
 */
@Entity(name = "r_volunteer_post")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VolunteerPost {
    // remember id has two components, so have created custom id
    @EmbeddedId
    private VolunteerPostId id = new VolunteerPostId();

    // volunteer component (many entries in table correspond to one volunteer)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @MapsId("volunteerId")
    private Volunteer volunteer;

    // post component (many entries in the table correspond to one post)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @MapsId("postId")
    private Post post;

    // status for this sign up (pending, accepted, rejected)
    @Enumerated(value = EnumType.STRING)
    private Status status;
}

