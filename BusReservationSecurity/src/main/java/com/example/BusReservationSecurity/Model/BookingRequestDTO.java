package com.example.BusReservationSecurity.Model;

import lombok.Data;

@Data
public class BookingRequestDTO {
    private String passengerName;
    private String travelDate;
    private int numberOfSeats;
    private Long busId;
    private String razorpayPaymentId;
}
