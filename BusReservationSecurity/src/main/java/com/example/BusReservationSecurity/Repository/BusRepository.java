package com.example.BusReservationSecurity.Repository;

import com.example.BusReservationSecurity.Model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    // You can add custom query methods if needed

    List<Bus> findByFromLocationAndToLocationAndTravelDate(String from, String to, LocalDate travelDate);


}