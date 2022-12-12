package com.example.pirateservice.repositories;

import com.example.pirateservice.models.Raid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaidRepository extends JpaRepository<Raid, Long> {
}
