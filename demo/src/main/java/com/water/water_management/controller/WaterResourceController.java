
package com.water.water_management.controller;

import com.water.water_management.entity.WaterResource;
import com.water.water_management.service.WaterResourceService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/api/resources")
public class WaterResourceController {

    private final WaterResourceService service;

    public WaterResourceController(WaterResourceService service) {
        this.service = service;
    }

    // Create a new water resource
    @PostMapping
    public ResponseEntity<WaterResource> createResource(
            @Valid @RequestBody WaterResource resource) {

        WaterResource saved = service.createResource(resource);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Get all water resources
    @GetMapping
    public List<WaterResource> getAllResources() {
        return service.getAllResources();
    }

    // Get a water resource by ID
    @GetMapping("/{id}")
    public ResponseEntity<WaterResource> getResourceById(
            @PathVariable Long id) {

        return service.getResourceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a water resource
    @PutMapping("/{id}")
    public ResponseEntity<WaterResource> updateResource(
            @PathVariable Long id,
            @Valid @RequestBody WaterResource resource) {

        return ResponseEntity.ok(service.updateResource(id, resource));
    }

    // Delete a water resource
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {

        service.deleteResource(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/availability")
    public ResponseEntity<Double> getAvailability(
            @PathVariable Long id) {

        return service.getResourceById(id)
                .map(resource -> ResponseEntity.ok(
                        service.calculateAvailabilityPercentage(resource)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<String> getWaterStatus(@PathVariable Long id) {

        return service.getResourceById(id)
                .map(resource -> ResponseEntity.ok(
                        service.calculateWaterStatus(resource)))
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/{id}/available-water")
    public ResponseEntity<Double> getAvailableWater(@PathVariable Long id) {
        return service.getResourceById(id)
                .map(resource -> ResponseEntity.ok(
                        service.calculateAvailableWater(resource)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/remaining-capacity")
    public ResponseEntity<Double> getRemainingCapacity(
            @PathVariable Long id) {

        return service.getResourceById(id)
                .map(resource -> ResponseEntity.ok(
                        service.calculateRemainingCapacity(resource)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/availability-summary")
    public ResponseEntity<Map<String, Object>> getAvailabilitySummary(
            @PathVariable Long id) {

        return service.getResourceById(id)
                .map(resource -> {
                    Map<String, Object> summary = new LinkedHashMap<>();

                    summary.put("resourceName", resource.getName());
                    summary.put("capacityM3", resource.getCapacityM3());
                    summary.put("currentLevelM3", resource.getCurrentLevelM3());
                    summary.put("remainingCapacityM3",
                            service.calculateRemainingCapacity(resource));
                    summary.put("availabilityPercentage",
                            service.calculateAvailabilityPercentage(resource));
                    summary.put("waterStatus",
                            service.calculateWaterStatus(resource));

                    return ResponseEntity.ok(summary);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
