
package com.water.water_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "water_resources")
public class WaterResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String resourceType;

    @NotBlank
    private String location;

    @NotNull
    @DecimalMin("0.01")
    private Double capacityM3;

    @NotNull
    @DecimalMin("0.0")
    private Double currentLevelM3;

    private String status;

    public WaterResource() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getCapacityM3() {
        return capacityM3;
    }

    public void setCapacityM3(Double capacityM3) {
        this.capacityM3 = capacityM3;
    }

    public Double getCurrentLevelM3() {
        return currentLevelM3;
    }

    public void setCurrentLevelM3(Double currentLevelM3) {
        this.currentLevelM3 = currentLevelM3;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
