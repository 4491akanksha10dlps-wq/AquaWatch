package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestServicesController {

    private final RequestServicesRepository repository;

    public RequestServicesController(RequestServicesRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<RequestServices> getAllRecords() {
        return repository.findAll();
    }

    @PostMapping
    public RequestServices addRecord(@RequestBody RequestServices record) {
        record.setStatus("Pending");
        return repository.save(record);
    }
}