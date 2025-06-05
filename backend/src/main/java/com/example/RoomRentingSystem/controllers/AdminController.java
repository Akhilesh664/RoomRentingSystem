package com.example.RoomRentingSystem.controllers;

import com.example.RoomRentingSystem.dto.RoomRequestDTO;
import com.example.RoomRentingSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/requests")
    public ResponseEntity<List<RoomRequestDTO>> getAllRequests() {
        return ResponseEntity.ok(customerService.getAllRequests());
    }

    @PostMapping("/request/{requestId}/status")
    public ResponseEntity<String> updateRequestStatus(@PathVariable String requestId, @RequestParam String status) {
        customerService.updateRequestStatus(requestId, status);
        return ResponseEntity.ok("Status updated");
    }

    @PostMapping("/bill")
    public ResponseEntity<String> uploadBill(@RequestParam("file") MultipartFile file, @RequestParam("customerId") String customerId,
                                             @RequestParam("billingMonth") String billingMonth, @RequestParam("amount") double amount,
                                             @RequestParam("dueDate") String dueDate) {
        customerService.uploadBill(file, customerId, billingMonth, amount, dueDate);
        return ResponseEntity.ok("Bill uploaded");
    }
}
