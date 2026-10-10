package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintRepository repository;

    public ComplaintController(ComplaintRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Complaint> getAllRecords() {
        return repository.findAll();
    }

    @PostMapping
    public Complaint addRecord(@RequestBody Complaint record) {
        record.setStatus("Pending");
        return repository.save(record);
    }
}