package com.water.water_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "water_distributions")
public class WaterDistribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String areaName;
    private Double allocatedVolumeM3;
    private String distributionDate;
    private String status; // e.g., "Scheduled", "In Progress", "Completed"

    public WaterDistribution() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAreaName() { return areaName; }
    public void setAreaName(String areaName) { this.areaName = areaName; }

    public Double getAllocatedVolumeM3() { return allocatedVolumeM3; }
    public void setAllocatedVolumeM3(Double allocatedVolumeM3) { this.allocatedVolumeM3 = allocatedVolumeM3; }

    public String getDistributionDate() { return distributionDate; }
    public void setDistributionDate(String distributionDate) { this.distributionDate = distributionDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}