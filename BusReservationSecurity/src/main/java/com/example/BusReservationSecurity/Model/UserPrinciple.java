package com.example.BusReservationSecurity.Model;


import com.example.BusReservationSecurity.Model.Users;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@RequiredArgsConstructor
public class UserPrinciple implements UserDetails {

    private final Users users;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+users.getRole().toUpperCase()));
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }
}
