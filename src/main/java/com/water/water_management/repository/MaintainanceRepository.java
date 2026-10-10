
package com.water.water_management.repository;

import com.water.water_management.entity.MaintainanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintainanceRepository
        extends JpaRepository<MaintainanceRequest, Long> {
}
