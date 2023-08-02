package com.volsync.volsyncproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.volsync.volsyncproject.pk.VolunteerPostId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity(name = "r_volunteer_post")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VolunteerPost implements Serializable {
    @EmbeddedId
    @JsonIgnore
    private VolunteerPostId id = new VolunteerPostId();

    @ManyToOne
    @MapsId("volunteerId")
    private Volunteer volunteer;

    @ManyToOne
    @MapsId("postId")
    private Post post;

    private Status status;
}

