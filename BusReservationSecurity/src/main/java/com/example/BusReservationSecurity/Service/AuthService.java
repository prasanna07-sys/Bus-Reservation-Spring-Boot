package com.example.BusReservationSecurity.Service;

import com.example.BusReservationSecurity.Model.Booking;
import com.example.BusReservationSecurity.Model.Bus;
import com.example.BusReservationSecurity.Model.Users;
import com.example.BusReservationSecurity.Repository.BookingRepository;
import com.example.BusReservationSecurity.Repository.BusRepository;
import com.example.BusReservationSecurity.Repository.UserRepository;
import com.example.BusReservationSecurity.Util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {



    private final UserRepository userRepository;
    private final BusRepository busRepository;
    private final BookingRepository bookingRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder bcrypt;

    public List<Users> getUsers(){
        return userRepository.findAll();
    }

    public void addUsers(Users users){
        users.setPassword(bcrypt.encode(users.getPassword()));
        userRepository.save(users);
    }

    public void deleteUsers(int id){
        userRepository.deleteById(id);
    }

    public Bus addBus(Bus bus) {
        return busRepository.save(bus);
    }

    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public String login(Users loginUser) {
        Users user = userRepository.findByUsername(loginUser.getUsername());

        if (user != null && bcrypt.matches(loginUser.getPassword(), user.getPassword())) {
            return jwtUtil.generateToken(user.getUsername());
        } else {
            return null;
        }
    }
}
