package com.example.BusReservationSecurity.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerName;

    private int numberOfSeats;

    private LocalDateTime bookingTime;

    private boolean paid;  // ✅ To track if payment was successful

    private String stripePaymentId; // ✅ To store Stripe payment reference

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;  // 🔗 Relationship with Bus

    private double fare;
    private String status; // Example: "PENDING", "CONFIRMED"
    private String paymentStatus; // Example: "UNPAID", "PAID"
}
