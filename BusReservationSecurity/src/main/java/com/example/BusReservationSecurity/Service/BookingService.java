package com.example.BusReservationSecurity.Service;

import com.example.BusReservationSecurity.Model.Booking;
import com.example.BusReservationSecurity.Model.BookingRequestDTO;
import com.example.BusReservationSecurity.Model.Bus;
import com.example.BusReservationSecurity.Repository.BookingRepository;
import com.example.BusReservationSecurity.Repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BusRepository busRepository;

    public Booking bookTicketWithPayment(BookingRequestDTO request) {
        Bus bus = busRepository.findById(request.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        // Validate travel date (if needed)
        LocalDate requestDate = LocalDate.parse(request.getTravelDate());
        if (!requestDate.equals(bus.getTravelDate())) {
            throw new RuntimeException("Invalid travel date for selected bus");
        }

        int available = bus.getAvailableSeats();
        int requested = request.getNumberOfSeats();

        if (available < requested) {
            throw new RuntimeException("Not enough seats available!");
        }

        // Calculate fare
        double totalFare = bus.getFarePerSeat() * requested;

        // Razorpay payment ID must be present
        if (request.getRazorpayPaymentId() == null || request.getRazorpayPaymentId().isEmpty()) {
            throw new RuntimeException("Missing Razorpay payment ID");
        }

        // Update bus seat count
        bus.setAvailableSeats(available - requested);
        busRepository.save(bus);

        // Save booking
        Booking booking = Booking.builder()
                .passengerName(request.getPassengerName())
                .numberOfSeats(requested)
                .bookingTime(LocalDateTime.now())
                .bus(bus)
                .fare(totalFare)
                .stripePaymentId(request.getRazorpayPaymentId())
                .status("CONFIRMED")
                .paymentStatus("PAID")
                .paid(true)
                .build();

        return bookingRepository.save(booking);
    }
}
