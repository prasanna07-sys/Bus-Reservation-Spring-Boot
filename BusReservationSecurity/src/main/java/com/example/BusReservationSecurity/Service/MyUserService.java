package com.example.BusReservationSecurity.Service;


import com.example.BusReservationSecurity.Model.UserPrinciple;
import com.example.BusReservationSecurity.Model.Users;
import com.example.BusReservationSecurity.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MyUserService implements UserDetailsService {

    private final UserRepository userRepository;

    // Used by Spring Security during login to load user from DB
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users users = userRepository.findByUsername(userName);

        if(users == null){
            throw new UsernameNotFoundException("Users not found");
        }
        else{
            return new UserPrinciple(users);
        }

    }
}
