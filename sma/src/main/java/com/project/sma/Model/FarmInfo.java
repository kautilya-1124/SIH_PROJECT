package com.project.sma.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "farm_info")
public class FarmInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(nullable = false, length = 6)
    private String pincode;

    @Column(nullable = false, length = 50)
    private String soilType;

    @Column(nullable = false, length = 50)
    private String irrigationType;

    @Column(nullable = false, length = 50)
    private String waterResource;

    private double area; // acre me hi store hoga
    
    @Column(nullable = false , length = 10000)
    private String recommendation;


    public String getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	private LocalDateTime createdAt = LocalDateTime.now();

    // **New field to link farmer**
    private Long farmerId;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public String getSoilType() { return soilType; }
    public void setSoilType(String soilType) { this.soilType = soilType; }

    public String getIrrigationType() { return irrigationType; }
    public void setIrrigationType(String irrigationType) { this.irrigationType = irrigationType; }

    public String getWaterResource() { return waterResource; }
    public void setWaterResource(String waterResource) { this.waterResource = waterResource; }

    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Long getFarmerId() { return farmerId; }
    public void setFarmerId(Long farmerId) { this.farmerId = farmerId; }
}
