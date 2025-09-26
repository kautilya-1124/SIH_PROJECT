package com.project.sma.dto;

public class Recommendation {
    private String cropRecommendation;
    private String soilAdvice;
    private String irrigationAdvice;
    private String fertilizerAdvice;

    // Getters and Setters
    public String getCropRecommendation() {
        return cropRecommendation;
    }
    public void setCropRecommendation(String cropRecommendation) {
        this.cropRecommendation = cropRecommendation;
    }

    public String getSoilAdvice() {
        return soilAdvice;
    }
    public void setSoilAdvice(String soilAdvice) {
        this.soilAdvice = soilAdvice;
    }

    public String getIrrigationAdvice() {
        return irrigationAdvice;
    }
    public void setIrrigationAdvice(String irrigationAdvice) {
        this.irrigationAdvice = irrigationAdvice;
    }

    public String getFertilizerAdvice() {
        return fertilizerAdvice;
    }
    public void setFertilizerAdvice(String fertilizerAdvice) {
        this.fertilizerAdvice = fertilizerAdvice;
    }
}
