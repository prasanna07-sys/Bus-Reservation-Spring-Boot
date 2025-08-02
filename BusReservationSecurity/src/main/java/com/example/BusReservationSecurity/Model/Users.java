package com.example.BusReservationSecurity.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users") // Optional, just to avoid conflict with SQL keyword
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String role; // "ROLE_USER" or "ROLE_ADMIN"
}
