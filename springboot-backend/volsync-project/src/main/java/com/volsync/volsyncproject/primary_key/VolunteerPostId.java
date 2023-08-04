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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((volunteerId == null) ? 0 : volunteerId.hashCode());
        result = prime * result
                + ((postId == null) ? 0 : postId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        VolunteerPostId other = (VolunteerPostId) obj;

        if (volunteerId == null) {
            if (other.volunteerId != null)
                return false;
        } else if (!volunteerId.equals(other.volunteerId))
            return false;

        if (postId == null) {
            if (other.postId != null)
                return false;
        } else if (!postId.equals(other.postId))
            return false;

        return true;
    }


}
