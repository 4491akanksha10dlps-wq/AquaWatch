package com.water.water_management.service;

import com.water.water_management.entity.WaterDistribution;
import com.water.water_management.repository.WaterDistributionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WaterDistributionService {

    private final WaterDistributionRepository repository;

    public WaterDistributionService(WaterDistributionRepository repository) {
        this.repository = repository;
    }

    public WaterDistribution createDistribution(WaterDistribution distribution) {
        if (distribution.getAllocatedVolumeM3() == null || distribution.getAllocatedVolumeM3() <= 0) {
            throw new IllegalArgumentException("Valid allocated volume is required");
        }
        distribution.setStatus("Scheduled");
        return repository.save(distribution);
    }

    public List<WaterDistribution> getAllDistributions() {
        return repository.findAll();
    }

    public Optional<WaterDistribution> getDistributionById(Long id) {
        return repository.findById(id);
    }
}
