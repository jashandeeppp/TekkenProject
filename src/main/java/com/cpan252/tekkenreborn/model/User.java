package com.cpan252.tekkenreborn.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@Entity(name = "users")     // Entity must not be named User because it is reserved key word
//@Entity annotation we are making a table in the database named users.
/*
 * It is User model esentially to implement the UserDetails interface.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class User implements UserDetails {
    // In this class we implemented the methods of the UserDetails interface.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;        // primary key automatically generated.


    private String username;
    private String password;

    @Override       
    public Collection<? extends GrantedAuthority> getAuthorities() {        // getAuthorities() user can have many authorities.
        if (username.equals("admin"))
            return List.of(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"));
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // returning true for all of the remaining methods
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
