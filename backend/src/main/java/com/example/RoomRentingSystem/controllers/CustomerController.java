package com.example.RoomRentingSystem.controllers;

import com.example.RoomRentingSystem.dto.CustomerBillDTO;
import com.example.RoomRentingSystem.dto.CustomerDTO;
import com.example.RoomRentingSystem.dto.RoomRequestDTO;
import com.example.RoomRentingSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/profile")
    public ResponseEntity<String> updateProfile(@RequestBody CustomerDTO customerDTO) {
        customerService.updateProfile(customerDTO);
        return ResponseEntity.ok("Profile updated");
    }

    @PostMapping("/document")
    public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file, @RequestParam("customerId") String customerId) {
        customerService.uploadDocument(file, customerId);
        return ResponseEntity.ok("Document uploaded");
    }

    @PostMapping("/request")
    public ResponseEntity<String> submitRoomRequest(@RequestBody RoomRequestDTO requestDTO) {
        customerService.submitRoomRequest(requestDTO);
        return ResponseEntity.ok("Room request submitted");
    }

    @GetMapping("/bills")
    public ResponseEntity<List<CustomerBillDTO>> getBills(@RequestParam("customerId") String customerId) {
        return ResponseEntity.ok(customerService.getBills(customerId));
    }
}
