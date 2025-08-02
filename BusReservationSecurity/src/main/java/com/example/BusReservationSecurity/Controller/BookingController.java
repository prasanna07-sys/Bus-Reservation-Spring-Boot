package com.example.BusReservationSecurity.Controller;

import com.example.BusReservationSecurity.Model.Booking;
import com.example.BusReservationSecurity.Model.BookingRequestDTO;
import com.example.BusReservationSecurity.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<Booking> bookTicket(@RequestBody BookingRequestDTO request) {
        Booking booking = bookingService.bookTicketWithPayment(request);
        return ResponseEntity.ok(booking);
    }
}

