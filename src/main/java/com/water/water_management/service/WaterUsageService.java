
package com.water.water_management.service;

import com.water.water_management.entity.WaterUsage;
import com.water.water_management.repository.WaterUsageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WaterUsageService {

    private final WaterUsageRepository repository;

    public WaterUsageService(WaterUsageRepository repository) {
        this.repository = repository;
    }

    public WaterUsage createUsage(WaterUsage usage) {
        if (usage.getConsumerName() == null
                || usage.getConsumerName().isBlank()) {
            throw new IllegalArgumentException(
                    "Consumer name is required");
        }

        if (usage.getQuantityM3() == null
                || usage.getQuantityM3() <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero");
        }

        if (usage.getUsageDate() == null) {
            throw new IllegalArgumentException(
                    "Usage date is required");
        }

        return repository.save(usage);
    }

    public List<WaterUsage> getAllUsage() {
        return repository.findAll();
    }

    public Optional<WaterUsage> getUsageById(Long id) {
        return repository.findById(id);
    }

    public WaterUsage updateUsage(Long id, WaterUsage updatedUsage) {
        WaterUsage existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Usage record not found: " + id));

        existing.setConsumerName(updatedUsage.getConsumerName());
        existing.setUsageType(updatedUsage.getUsageType());
        existing.setLocation(updatedUsage.getLocation());
        existing.setQuantityM3(updatedUsage.getQuantityM3());
        existing.setUsageDate(updatedUsage.getUsageDate());

        return createUsage(existing);
    }

    public void deleteUsage(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Usage record not found: " + id);
        }

        repository.deleteById(id);
    }
}
