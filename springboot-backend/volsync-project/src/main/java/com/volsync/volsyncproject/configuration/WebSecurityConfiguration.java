package com.volsync.volsyncproject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// annotations needed for spring security to function
@Configuration
@EnableWebSecurity
/**
 * If username (UNIQUE) not recognized, then CustomUserDetailsService does not successfully return UserDetails object,
 * so authentication has failed. Otherwise, UserDetails object has been returned successfully and has been given to
 * provider so provider will deal with password (why we pass bcrypt encoder) + roles
 *
 * CustomUserDetailsService returns us a CustomUserDetails object which contains user information like Username, Password,
 * Roles, and some expiration settings
 *
 *
 */
public class WebSecurityConfiguration {


    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    AuthenticationProvider authenticationProvider() {
        // DAO = data access object (accessing data within database to determine if user is valid/exists or not)
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // contains more information about this user (username, password, roles)
        provider.setUserDetailsService(userDetailsService);

        // using bcrypt to encode passwords
        provider.setPasswordEncoder(new BCryptPasswordEncoder());

        // so we have found the username (UserDetailsService created successfully),
        // but auth provider authenticates password + role management (authorization)
        return provider;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        // we will permit all willing to go to root page, but to go to /home or /admin they need to be authenticated
        // and have the proper role
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth -> {
                    // these first three patterns are needed for the registration process, which is permitted to all
                    // users (anyone can register)
                    auth.requestMatchers("/api/v1/users").permitAll();
                    auth.requestMatchers("/api/v1/organizations").permitAll();
                    auth.requestMatchers("/api/v1/volunteers").permitAll();
                    auth.requestMatchers("/api/v1/login/volunteer").hasAuthority("VOL");
                    auth.requestMatchers("/api/v1/login/organization").hasAuthority("ORG");
                })
                .httpBasic(Customizer.withDefaults())   // transmits credentials as username/password pairs
                .build();
    }


}

