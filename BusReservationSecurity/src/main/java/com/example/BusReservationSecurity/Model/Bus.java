package com.example.BusReservationSecurity.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Bus {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String busNumber;
        private String fromLocation;
        private String toLocation;
        private LocalDate travelDate;
        private double farePerSeat;

        private int totalSeats;
        private int availableSeats;
    }

