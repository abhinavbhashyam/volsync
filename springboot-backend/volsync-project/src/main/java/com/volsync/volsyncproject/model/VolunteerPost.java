package com.volsync.volsyncproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.volsync.volsyncproject.primary_key.VolunteerPostId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Entity corresponding to our volunteer_post join table
 */
@Entity(name = "r_volunteer_post")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VolunteerPost implements Serializable {
    // remember id has two components, so have created custom id
    @EmbeddedId
    @JsonIgnore
    private VolunteerPostId id = new VolunteerPostId();

    // volunteer component (many entries in table correspond to one volunteer)
    @ManyToOne
    @MapsId("volunteerId")
    private Volunteer volunteer;

    // post component (many entries in the table correspond to one post)
    @ManyToOne
    @MapsId("postId")
    private Post post;

    // status for this sign up (pending, accepted, rejected)
    private Status status;
}

