package com.water.water_management.controller;

import com.water.water_management.entity.WaterDistribution;
import com.water.water_management.service.WaterDistributionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/distributions")
public class WaterDistributionController {

    private final WaterDistributionService service;

    public WaterDistributionController(WaterDistributionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WaterDistribution> createDistribution(@RequestBody WaterDistribution distribution) {
        WaterDistribution saved = service.createDistribution(distribution);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<WaterDistribution> getAllDistributions() {
        return service.getAllDistributions();
    }
}