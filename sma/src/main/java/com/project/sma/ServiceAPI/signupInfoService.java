package com.project.sma.ServiceAPI;

import com.project.sma.Model.signupInfo;
import com.project.sma.Repository.signupInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class signupInfoService {

    @Autowired
    private signupInfoRepo repo;

    public signupInfo save(signupInfo info) {
        return repo.save(info);
    }

    public List<signupInfo> getAll() {
        return repo.findAll();
    }
    public signupInfo findByEmail(String email) {
        return repo.findByEmail(email);
    }

}
