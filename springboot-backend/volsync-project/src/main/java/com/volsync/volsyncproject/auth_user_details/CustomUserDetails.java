package com.volsync.volsyncproject.auth_user_details;

import com.volsync.volsyncproject.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        super();
        this.user = user;
    }

    // retrieve the authority/role of the user
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
    }

    // retrieve the password of the user
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // retrieve username of user
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // need below methods to return true to make sure auth doesn't fail for unintended reasons
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
