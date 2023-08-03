package com.volsync.volsyncproject.primary_key;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Primary key for join table contains two components: volunteer and post id
 */
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VolunteerPostId implements Serializable {

    private Long volunteerId;
    private Long postId;


}
