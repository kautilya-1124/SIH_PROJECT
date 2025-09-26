package com.project.sma.Repository;

import com.project.sma.Model.signupInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface signupInfoRepo extends JpaRepository<signupInfo, Long> {

    signupInfo findByEmail(String email); // optional
    
}
