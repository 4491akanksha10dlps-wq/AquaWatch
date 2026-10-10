
package com.water.water_management.repository;

import com.water.water_management.entity.WaterResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterResourceRepository
        extends JpaRepository<WaterResource, Long> {

}
