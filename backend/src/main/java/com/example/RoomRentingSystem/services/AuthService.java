package com.example.RoomRentingSystem.services;

import com.example.RoomRentingSystem.config.JwtUtil;
import com.example.RoomRentingSystem.dto.CustomerDTO;
import com.example.RoomRentingSystem.entities.Customer;
import com.example.RoomRentingSystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public void register(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        customer.setAddress(customerDTO.getAddress());
        customerRepository.save(customer);
    }

    public String login(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null && passwordEncoder.matches(password, customer.getPassword())) {
            return jwtUtil.generateToken(email, "CUSTOMER");
        }
        throw new RuntimeException("Invalid credentials");
    }

    public String generateAdminToken() {
        return jwtUtil.generateToken("admin@example.com", "ADMIN");
    }

}
