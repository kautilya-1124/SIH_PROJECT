package com.project.sma.ServiceAPI;

import com.project.sma.Model.FarmInfo;
import com.project.sma.Repository.FarmInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmInfoService {

    @Autowired
    private FarmInfoRepo farmInfoRepo;

    public FarmInfo saveFarm(FarmInfo farmInfo) {
        return farmInfoRepo.save(farmInfo);
    }

    public List<FarmInfo> getAllFarms() {
        return farmInfoRepo.findAll();
    }

    // **New method to get farms of a farmer**
    public List<FarmInfo> getFarmsByFarmer(Long farmerId) {
        return farmInfoRepo.findByFarmerId(farmerId);
    }
}
