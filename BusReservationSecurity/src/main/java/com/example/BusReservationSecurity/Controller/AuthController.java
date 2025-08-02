package com.example.BusReservationSecurity.Controller;


import com.example.BusReservationSecurity.Model.Booking;
import com.example.BusReservationSecurity.Model.Bus;
import com.example.BusReservationSecurity.Model.Users;
import com.example.BusReservationSecurity.Repository.BookingRepository;
import com.example.BusReservationSecurity.Repository.BusRepository;
import com.example.BusReservationSecurity.Repository.UserRepository;
import com.example.BusReservationSecurity.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getUsers(){
        return ResponseEntity.ok(authService.getUsers());
    }

    @PostMapping("/add/users")
    public ResponseEntity<Void> addUsers(@RequestBody Users users){
        authService.addUsers(users);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/users/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable("id") int id){
        authService.deleteUsers(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add/buses")
    public ResponseEntity<Bus> addBus(@RequestBody Bus bus) {
        return ResponseEntity.ok(authService.addBus(bus));
    }

    @DeleteMapping("delete/buses/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        authService.deleteBus(id);
        return ResponseEntity.ok().build();
    }

    // ✅ Get all bookings
    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(authService.getAllBookings());
    }

    // ✅ Get booking by ID
    @GetMapping("bookings/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return authService.getBookingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users loginUser) {
        String token = authService.login(loginUser);
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}