package com.example.RoomRentingSystem.repositories;

import com.example.RoomRentingSystem.entities.CustomerBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerBillRepository extends JpaRepository<CustomerBill, UUID> {
    List<CustomerBill> findByCustomerId(UUID customerId);
}