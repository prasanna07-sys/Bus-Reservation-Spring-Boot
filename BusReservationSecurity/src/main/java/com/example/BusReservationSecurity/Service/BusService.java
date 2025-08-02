package com.example.BusReservationSecurity.Service;

import com.example.BusReservationSecurity.Model.Bus;
import com.example.BusReservationSecurity.Repository.BookingRepository;
import com.example.BusReservationSecurity.Repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusService {


    private final BusRepository busRepository;
    private final BookingRepository bookingRepository;

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Optional<Bus> getBusById(Long id) {
        return busRepository.findById(id);
    }



    public List<Bus> searchBuses(String from, String to, LocalDate travelDate) {
        return busRepository.findByFromLocationAndToLocationAndTravelDate(from, to, travelDate);
    }
    public int getRemainingSeats(Long busId, LocalDate date) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        Integer bookedSeats = bookingRepository.findTotalBookedSeats(busId, date);
        if (bookedSeats == null) bookedSeats = 0;

        return bus.getTotalSeats() - bookedSeats;
    }





}
