
package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/water")
public class WaterConsumptionController {

    private final WaterConsumptionRepository repository;

    public WaterConsumptionController(
            WaterConsumptionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<WaterConsumption> getAllRecords() {
        return repository.findAll();
    }

    @PostMapping
    public WaterConsumption addRecord(
            @RequestBody WaterConsumption record) {
        return repository.save(record);
    }
}