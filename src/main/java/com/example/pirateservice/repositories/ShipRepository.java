package com.example.pirateservice.repositories;

import com.example.pirateservice.models.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship, Long> {

}
