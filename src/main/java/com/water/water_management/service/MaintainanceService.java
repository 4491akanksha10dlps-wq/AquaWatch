
package com.water.water_management.service;

import com.water.water_management.entity.MaintainanceRequest;
import com.water.water_management.repository.MaintainanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintainanceService {

    private final MaintainanceRepository repository;

    public MaintainanceService(MaintainanceRepository repository) {
        this.repository = repository;
    }

    public MaintainanceRequest createRequest(MaintainanceRequest request) {
        if (request.getAssetName() == null
                || request.getAssetName().isBlank()) {
            throw new IllegalArgumentException("Asset name is required");
        }

        if (request.getLocation() == null
                || request.getLocation().isBlank()) {
            throw new IllegalArgumentException("Location is required");
        }

        if (request.getReportedDate() == null) {
            throw new IllegalArgumentException("Reported date is required");
        }

        if (request.getPriority() == null
                || !List.of("LOW", "MEDIUM", "HIGH")
                .contains(request.getPriority().toUpperCase())) {
            throw new IllegalArgumentException(
                    "Priority must be LOW, MEDIUM, or HIGH");
        }

        if (request.getStatus() == null
                || request.getStatus().isBlank()) {
            request.setStatus("REPORTED");
        }

        return repository.save(request);
    }

    public List<MaintainanceRequest> getAllRequests() {
        return repository.findAll();
    }

    public Optional<MaintainanceRequest> getRequestById(Long id) {
        return repository.findById(id);
    }

    public MaintainanceRequest updateRequest(
            Long id, MaintainanceRequest updatedRequest) {

        MaintainanceRequest existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Maintenance request not found: " + id));

        existing.setAssetName(updatedRequest.getAssetName());
        existing.setIssueType(updatedRequest.getIssueType());
        existing.setLocation(updatedRequest.getLocation());
        existing.setDescription(updatedRequest.getDescription());
        existing.setPriority(updatedRequest.getPriority());
        existing.setStatus(updatedRequest.getStatus());
        existing.setReportedDate(updatedRequest.getReportedDate());

        return createRequest(existing);
    }

    public void deleteRequest(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Maintenance request not found: " + id);
        }

        repository.deleteById(id);
    }
}
