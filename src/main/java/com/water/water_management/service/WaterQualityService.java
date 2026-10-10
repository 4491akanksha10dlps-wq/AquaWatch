
package com.water.water_management.service;

import com.water.water_management.entity.WaterQuality;
import com.water.water_management.repository.WaterQualityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WaterQualityService {

    private final WaterQualityRepository repository;

    public WaterQualityService(WaterQualityRepository repository) {
        this.repository = repository;
    }

    public WaterQuality createQuality(WaterQuality quality) {
        if (quality.getWaterSource() == null
                || quality.getWaterSource().isBlank()) {
            throw new IllegalArgumentException(
                    "Water source is required");
        }

        if (quality.getLocation() == null
                || quality.getLocation().isBlank()) {
            throw new IllegalArgumentException(
                    "Location is required");
        }

        if (quality.getPh() == null
                || quality.getPh() < 0
                || quality.getPh() > 14) {
            throw new IllegalArgumentException(
                    "pH must be between 0 and 14");
        }

        if (quality.getTurbidity() == null
                || quality.getTurbidity() < 0) {
            throw new IllegalArgumentException(
                    "Turbidity cannot be negative");
        }

        if (quality.getDissolvedOxygen() == null
                || quality.getDissolvedOxygen() < 0) {
            throw new IllegalArgumentException(
                    "Dissolved oxygen cannot be negative");
        }

        if (quality.getTestDate() == null) {
            throw new IllegalArgumentException(
                    "Test date is required");
        }

        quality.setStatus(calculateStatus(quality));

        return repository.save(quality);
    }

    public List<WaterQuality> getAllQualityRecords() {
        return repository.findAll();
    }

    public Optional<WaterQuality> getQualityById(Long id) {
        return repository.findById(id);
    }

    public WaterQuality updateQuality(
            Long id, WaterQuality updatedQuality) {

        WaterQuality existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Water quality record not found: " + id));

        existing.setWaterSource(updatedQuality.getWaterSource());
        existing.setLocation(updatedQuality.getLocation());
        existing.setPh(updatedQuality.getPh());
        existing.setTurbidity(updatedQuality.getTurbidity());
        existing.setDissolvedOxygen(
                updatedQuality.getDissolvedOxygen());
        existing.setTestDate(updatedQuality.getTestDate());

        return createQuality(existing);
    }

    public void deleteQuality(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Water quality record not found: " + id);
        }

        repository.deleteById(id);
    }

    private String calculateStatus(WaterQuality quality) {
        double ph = quality.getPh();
        double turbidity = quality.getTurbidity();
        double oxygen = quality.getDissolvedOxygen();

        if (ph < 6.5 || ph > 8.5
                || turbidity > 5
                || oxygen < 5) {
            return "WARNING";
        }

        return "GOOD";
    }
}
