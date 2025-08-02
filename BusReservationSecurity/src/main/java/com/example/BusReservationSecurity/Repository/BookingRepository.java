package com.example.BusReservationSecurity.Repository;

import com.example.BusReservationSecurity.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT SUM(b.numberOfSeats) FROM Booking b WHERE b.bus.id = :busId AND b.bus.travelDate = :date")
    Integer findTotalBookedSeats(@Param("busId") Long busId, @Param("date") LocalDate date);

}