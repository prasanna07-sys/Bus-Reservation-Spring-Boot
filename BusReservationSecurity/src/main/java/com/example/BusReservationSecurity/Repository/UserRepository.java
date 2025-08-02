package com.example.BusReservationSecurity.Repository;

import com.example.BusReservationSecurity.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {


    Users findByUsername(String username); // used in login


}
