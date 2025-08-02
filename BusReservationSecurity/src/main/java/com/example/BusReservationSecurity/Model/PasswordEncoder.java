package com.example.BusReservationSecurity.Model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    public static void main(String[] args){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

        String password = "prasanna123";

        String enPassword = bCryptPasswordEncoder.encode(password);

        System.out.println(enPassword+":");

    }
}
