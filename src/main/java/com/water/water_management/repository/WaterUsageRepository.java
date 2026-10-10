
package com.water.water_management.repository;

import com.water.water_management.entity.WaterUsage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterUsageRepository
        extends JpaRepository<WaterUsage, Long> {
}
