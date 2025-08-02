package com.example.BusReservationSecurity.Controller;

import com.example.BusReservationSecurity.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"}) // Optional if frontend is used
public class PaymentController {

    private final PaymentService paymentService;

    // ✅ Generate Razorpay order
    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestBody Map<String, Double> payload) {
        double amount = payload.get("amount");
        String orderJson = paymentService.createRazorpayOrder(amount);
        return ResponseEntity.ok(orderJson);
    }

}
