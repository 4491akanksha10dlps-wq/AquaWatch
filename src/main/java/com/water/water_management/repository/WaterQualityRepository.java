
package com.water.water_management.repository;

import com.water.water_management.entity.WaterQuality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterQualityRepository
        extends JpaRepository<WaterQuality, Long> {
}

