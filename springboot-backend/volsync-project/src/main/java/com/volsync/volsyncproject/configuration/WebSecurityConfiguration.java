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

@Configuration
@EnableWebSecurity

/**
 * Configuration class for our application which uses Spring Security for auth-related tasks
 */
public class WebSecurityConfiguration {


    // reference to userDetailsService class (needed by DaoAuthenticationProvider)
    private final UserDetailsService userDetailsService;

    /**
     * Dependency injection for userDetailsService
     * @param userDetailsService reference to userDetailsService
     */
    @Autowired
    public WebSecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Configures an AuthenticationProvider
     * @return an AuthenticationProvider that houses user information to authenticate/authorize them
     */
    @Bean
    AuthenticationProvider authenticationProvider() {
        // auth provider that takes in info about user to authenticate them
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // contains more information about this user (username, password, roles)

        // if username not found, loadUserByUsername fails in CustomUserDetailsService, throwing an exception
            // -> authentication failed

        // if username is found, loadUserByUsername returns a CustomUserDetails which contains information
        // like the user's username, password, roles, etc.
            // -> auth successful IF password checks out AND role checks out to access specific endpoint
        provider.setUserDetailsService(userDetailsService);

        // using bcrypt to encode passwords
        provider.setPasswordEncoder(new BCryptPasswordEncoder());

        return provider;
    }

    /**
     * Security configuration for various HTTP endpoints
     * @param http httpSecurity reference
     * @return the SecurityFilterChain for configuring authentication with various HTTP endpoints
     * @throws Exception exception for failure in generating the SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        // we will permit all willing to go to root page, but to go to /home or /admin they need to be authenticated
        // and have the proper role
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth -> {
                    // these first three patterns are needed for the registration process, which does not require authentication
                    auth.requestMatchers("/api/v1/users").permitAll();
                    auth.requestMatchers("/api/v1/organizations").permitAll();
                    auth.requestMatchers("/api/v1/volunteers").permitAll();
                    auth.requestMatchers("/api/v1/posts").permitAll();
                    // log in requires auth
                    auth.requestMatchers("/api/v1/login/volunteer").hasAuthority("VOL");
                    auth.requestMatchers("/api/v1/login/organization").hasAuthority("ORG");
                })
                .httpBasic(Customizer.withDefaults())   // transmits credentials as username/password pairs
                .build();
    }


}

