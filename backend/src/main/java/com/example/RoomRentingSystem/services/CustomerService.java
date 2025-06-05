package com.example.RoomRentingSystem.services;

import com.example.RoomRentingSystem.dto.CustomerBillDTO;
import com.example.RoomRentingSystem.dto.CustomerDTO;
import com.example.RoomRentingSystem.dto.RoomRequestDTO;
import com.example.RoomRentingSystem.entities.*;
import com.example.RoomRentingSystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private RoomRequestRepository roomRequestRepository;

    @Autowired
    private CustomerBillRepository customerBillRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public void updateProfile(CustomerDTO customerDTO){
        Customer customer = customerRepository.findById(UUID.fromString(customerDTO.getId()))
                .orElseThrow(()-> new RuntimeException("Customer not Found."));
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customer.setAddress(customerDTO.getAddress());
        customerRepository.save(customer);
    }

    public void uploadDocument(MultipartFile file, String customerId){
        try {
            Customer customer = customerRepository.findById(UUID.fromString(customerId))
                    .orElseThrow(()-> new RuntimeException("Customer not Found."));
            String fileUrl = fileStorageService.storeFile(file);
            Document document = new Document();
            document.setCustomer(customer);
            document.setFileName(file.getOriginalFilename());
            document.setFileUrl(fileUrl);
            document.setUploadedOn(LocalDateTime.now());
            documentRepository.save(document);
        } catch (Exception e){
            throw new RuntimeException("Failed to upload document: " + e.getMessage());
        }
    }

    public void submitRoomRequest(RoomRequestDTO requestDTO){
        Customer customer = customerRepository.findById(UUID.fromString(requestDTO.getCustomerId()))
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        RoomRequest request = new RoomRequest();
        request.setCustomer(customer);
        request.setRoomType(requestDTO.getRoomType());
        request.setBudget(requestDTO.getBudget());
        request.setNotes(requestDTO.getNotes());
        request.setStatus("PENDING");
        request.setRequestedOn(LocalDateTime.now());
        roomRequestRepository.save(request);
    }

    public List<CustomerBillDTO> getBills(String customerId){
        List<CustomerBill> bills = customerBillRepository.findByCustomerId(UUID.fromString(customerId));
        return bills.stream().map(bill-> {
            CustomerBillDTO dto = new CustomerBillDTO();
            dto.setId(bill.getId().toString());
            dto.setCustomerId(bill.getCustomer().getId().toString());
            dto.setImageUrl(bill.getImageUrl());
            dto.setBillingMonth(bill.getBillingMonth());
            dto.setAmount(bill.getAmount());
            dto.setDueDate(bill.getDueDate().toString());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<RoomRequestDTO> getAllRequests() {
        return roomRequestRepository.findAll().stream().map(req -> {
            RoomRequestDTO dto = new RoomRequestDTO();
            dto.setId(req.getId().toString());
            dto.setCustomerId(req.getCustomer().getId().toString());
            dto.setCustomerName(req.getCustomer().getName());
            dto.setRoomType(req.getRoomType());
            dto.setBudget(req.getBudget());
            dto.setNotes(req.getNotes());
            dto.setStatus(req.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }

    public void updateRequestStatus(String requestId, String status) {
        RoomRequest request = roomRequestRepository.findById(UUID.fromString(requestId))
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(status);
        roomRequestRepository.save(request);
    }

    public void uploadBill(MultipartFile file, String customerId, String billingMonth, double amount, String dueDate) {
        try {
            Customer customer = customerRepository.findById(UUID.fromString(customerId))
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            String fileUrl = fileStorageService.storeFile(file);
            CustomerBill bill = new CustomerBill();
            bill.setCustomer(customer);
            bill.setImageUrl(fileUrl);
            bill.setBillingMonth(billingMonth);
            bill.setAmount(amount);
            bill.setDueDate(LocalDate.parse(dueDate));
            bill.setUploadedAt(LocalDateTime.now());
            customerBillRepository.save(bill);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload bill: " + e.getMessage());
        }
    }


}
