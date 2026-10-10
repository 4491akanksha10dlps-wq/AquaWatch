
package com.water.water_management.controller;

import com.water.water_management.entity.WaterUsage;
import com.water.water_management.service.WaterUsageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usage")
public class WaterUsageController {

    private final WaterUsageService service;

    public WaterUsageController(WaterUsageService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WaterUsage> createUsage(
            @RequestBody WaterUsage usage) {

        WaterUsage saved = service.createUsage(usage);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<WaterUsage> getAllUsage() {
        return service.getAllUsage();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaterUsage> getUsageById(
            @PathVariable Long id) {

        return service.getUsageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WaterUsage> updateUsage(
            @PathVariable Long id,
            @RequestBody WaterUsage usage) {

        return ResponseEntity.ok(service.updateUsage(id, usage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsage(
            @PathVariable Long id) {

        service.deleteUsage(id);

        return ResponseEntity.noContent().build();
    }
}
