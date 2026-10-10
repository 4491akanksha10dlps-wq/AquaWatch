
package com.water.water_management.service;

import com.water.water_management.entity.WaterResource;
import com.water.water_management.repository.WaterResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WaterResourceService {

    private final WaterResourceRepository repository;

    public WaterResourceService(WaterResourceRepository repository) {
        this.repository = repository;
    }

    public WaterResource createResource(WaterResource resource) {
        if (resource.getCapacityM3() == null
                || resource.getCurrentLevelM3() == null) {
            throw new IllegalArgumentException(
                    "Capacity and current water level are required");
        }

        if (resource.getCapacityM3() <= 0
                || resource.getCurrentLevelM3() < 0
                || resource.getCurrentLevelM3()
                > resource.getCapacityM3()) {
            throw new IllegalArgumentException(
                    "Invalid capacity or current water level");
        }

        return repository.save(resource);
    }

    public List<WaterResource> getAllResources() {
        return repository.findAll();
    }

    public Optional<WaterResource> getResourceById(Long id) {
        return repository.findById(id);
    }

    public WaterResource updateResource(
            Long id, WaterResource updatedResource) {

        WaterResource existing = repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Water resource not found: " + id));

        existing.setName(updatedResource.getName());
        existing.setResourceType(updatedResource.getResourceType());
        existing.setLocation(updatedResource.getLocation());
        existing.setCapacityM3(updatedResource.getCapacityM3());
        existing.setCurrentLevelM3(updatedResource.getCurrentLevelM3());
        existing.setStatus(updatedResource.getStatus());

        return createResource(existing);
    }

    public void deleteResource(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Water resource not found: " + id);
        }

        repository.deleteById(id);
    }

    public double calculateAvailabilityPercentage(
            WaterResource resource) {

        if (resource.getCapacityM3() == null
                || resource.getCapacityM3() <= 0
                || resource.getCurrentLevelM3() == null) {
            throw new IllegalArgumentException(
                    "Valid capacity and current water level are required");
        }

        return (resource.getCurrentLevelM3()
                / resource.getCapacityM3()) * 100.0;
    }

    public String calculateWaterStatus(WaterResource resource) {

        double percentage = calculateAvailabilityPercentage(resource);

        if (percentage <= 20) {
            return "CRITICAL";
        } else if (percentage <= 50) {
            return "LOW";
        } else {
            return "NORMAL";
        }
    }

    public double calculateAvailableWater(WaterResource resource) {
        if (resource.getCurrentLevelM3() == null) {
            return 0.0;
        }

        return Math.max(0.0, resource.getCurrentLevelM3());
    }

    public double calculateRemainingCapacity(WaterResource resource) {
        if (resource.getCapacityM3() == null
                || resource.getCurrentLevelM3() == null) {
            return 0.0;
        }

        return Math.max(
                0.0,
                resource.getCapacityM3() - resource.getCurrentLevelM3()
        );
    }

}
