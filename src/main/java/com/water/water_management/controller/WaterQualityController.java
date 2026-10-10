
package com.water.water_management.controller;

import com.water.water_management.entity.WaterQuality;
import com.water.water_management.service.WaterQualityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quality")
public class WaterQualityController {

    private final WaterQualityService service;

    public WaterQualityController(WaterQualityService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WaterQuality> createQuality(
            @RequestBody WaterQuality quality) {

        WaterQuality saved = service.createQuality(quality);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<WaterQuality> getAllQualityRecords() {
        return service.getAllQualityRecords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaterQuality> getQualityById(
            @PathVariable Long id) {

        return service.getQualityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WaterQuality> updateQuality(
            @PathVariable Long id,
            @RequestBody WaterQuality quality) {

        return ResponseEntity.ok(
                service.updateQuality(id, quality));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuality(
            @PathVariable Long id) {

        service.deleteQuality(id);

        return ResponseEntity.noContent().build();
    }
}
