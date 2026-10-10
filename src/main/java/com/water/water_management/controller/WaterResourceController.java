
package com.water.water_management.conroller;

import com.water.water_management.entity.WaterResource;
import com.water.water_management.service.WaterResourceService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


}
