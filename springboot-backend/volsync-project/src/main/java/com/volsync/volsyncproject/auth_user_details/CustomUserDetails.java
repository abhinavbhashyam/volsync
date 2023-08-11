package com.volsync.volsyncproject.auth_user_details;

import com.volsync.volsyncproject.model.Role;
import com.volsync.volsyncproject.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Wrapper class around the user that we are trying to authenticate (needed for AuthenticationProvider).
 * Contains more information (for example, authorities) about the user we are trying to authenticate
 */
public class CustomUserDetails implements UserDetails {

    // user we are trying to authenticate
    private User user;

    /**
     * Initializes user instance
     * @param user user object corresponding to the user we are trying to authenticate
     */
    public CustomUserDetails(User user) {
        super();
        this.user = user;
    }

    /**
     * Retrieve the authority of the user
     * @return the roles/authorities of the user
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // return authority based on role
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole() == Role.ORG ? "ORG" : "VOL"));
    }

    /**
     * Get the password of the user
     * @return the password of this user
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Get the username of the user
     * @return the username of this user
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Gets the user corresponding to this CustomUserDetails object
     * @return the user corresponding to this CustomUserDetails object
     */
    public User getUser() {
        return this.user;
    }

   /*
   Need below methods to return true to make sure auth doesn't fail for unintended reasons
    */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
