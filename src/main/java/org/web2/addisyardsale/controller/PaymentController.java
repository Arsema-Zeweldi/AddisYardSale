package org.web2.addisyardsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web2.addisyardsale.dto.PaymentRequest;
import org.web2.addisyardsale.service.PaymentService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(originPatterns = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/chapa")
    public ResponseEntity<Map<String, Object>> initiatePayment(@RequestBody PaymentRequest request) {
        try {
            Map<String, Object> response = paymentService.initializePayment(request);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Payment initiation failed");
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
