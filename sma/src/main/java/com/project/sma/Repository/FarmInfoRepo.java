package com.project.sma.Repository;

import com.project.sma.Model.FarmInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmInfoRepo extends JpaRepository<FarmInfo, Long> {
    List<FarmInfo> findByFarmerId(Long farmerId);
}
