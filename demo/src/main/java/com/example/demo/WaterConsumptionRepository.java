
package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterConsumptionRepository
        extends JpaRepository<WaterConsumption, Long> {
}