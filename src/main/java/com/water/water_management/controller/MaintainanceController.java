
package com.water.water_management.controller;

import com.water.water_management.entity.MaintainanceRequest;
import com.water.water_management.service.MaintainanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
public class MaintainanceController {

    private final MaintainanceService service;

    public MaintainanceController(MaintainanceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MaintainanceRequest> createRequest(
            @RequestBody MaintainanceRequest request) {

        MaintainanceRequest saved = service.createRequest(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<MaintainanceRequest> getAllRequests() {
        return service.getAllRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintainanceRequest> getRequestById(
            @PathVariable Long id) {

        return service.getRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintainanceRequest> updateRequest(
            @PathVariable Long id,
            @RequestBody MaintainanceRequest request) {

        return ResponseEntity.ok(service.updateRequest(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(
            @PathVariable Long id) {

        service.deleteRequest(id);

        return ResponseEntity.noContent().build();
    }
}
