package com.water.water_management.repository;

import com.water.water_management.entity.WaterDistribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterDistributionRepository extends JpaRepository<WaterDistribution, Long> {
}