package com.example.RoomRentingSystem.repositories;

import com.example.RoomRentingSystem.entities.RoomRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface RoomRequestRepository extends JpaRepository<RoomRequest, UUID> {
}
