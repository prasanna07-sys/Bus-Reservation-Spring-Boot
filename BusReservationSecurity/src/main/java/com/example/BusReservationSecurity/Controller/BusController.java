package com.example.BusReservationSecurity.Controller;

import com.example.BusReservationSecurity.Model.Bus;
import com.example.BusReservationSecurity.Service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/buses")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
public class BusController {

    private final BusService busService;

    // ✅ Add a new bus


    // ✅ Get all buses
    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses() {
        return ResponseEntity.ok(busService.getAllBuses());
    }

    // ✅ Get a single bus by ID
    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable Long id) {
        return busService.getBusById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Bus>> searchBuses(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String date) {
        LocalDate travelDate = LocalDate.parse(date);
        return ResponseEntity.ok(busService.searchBuses(from, to, travelDate));
    }

    @GetMapping("/{busId}/available-seats")
    public ResponseEntity<String> getAvailableSeats(
            @PathVariable Long busId,
            @RequestParam String date) {

        LocalDate travelDate = LocalDate.parse(date);
        int available = busService.getRemainingSeats(busId, travelDate);
        return ResponseEntity.ok("Available seats: " + available);
    }


    // ✅ Delete bus by ID

}
