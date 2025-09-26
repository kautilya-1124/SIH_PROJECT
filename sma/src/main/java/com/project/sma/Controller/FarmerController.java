package com.project.sma.Controller;

import com.project.sma.Model.FarmInfo;
import com.project.sma.Model.signupInfo;
import com.project.sma.ServiceAPI.FarmInfoService;
import com.project.sma.ServiceAPI.OpenAiService;
import com.project.sma.ServiceAPI.signupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/farm")
public class FarmerController {

    @Autowired
    private signupInfoService signupService;

    @Autowired
    private FarmInfoService farmInfoService;
    
    

    @Autowired
    private HttpSession session;
    
    
    

    // ==================== Farmer Dashboard ====================
    @GetMapping("/Dashboard")
    public String showDashboard(Model model) {
        if (session.getAttribute("loggedInFarmer") == null) {
            return "redirect:/farm/FarmerLogin";
        }

        signupInfo farmer = (signupInfo) session.getAttribute("loggedInFarmer");
        model.addAttribute("farmer", farmer);

        List<FarmInfo> farms = farmInfoService.getFarmsByFarmer(farmer.getId());
        model.addAttribute("farms", farms);

        return "farmer/FarmerDashboard";
    }

    // ==================== Add Farm Form ====================
    @GetMapping("/AddFarm")
    public String showAddFarmForm(Model model) {
        model.addAttribute("farmInfo", new FarmInfo());
        return "farmer/AddFarm";
    }

    @PostMapping("/AddFarm")
    public String saveFarm(@ModelAttribute FarmInfo farmInfo) {
        signupInfo farmer = (signupInfo) session.getAttribute("loggedInFarmer");
        farmInfo.setFarmerId(farmer.getId());
        farmInfoService.saveFarm(farmInfo);
        return "redirect:/farm/Dashboard"; // ✅ fixed redirect
    }
    
    
 //================================Recommandation page start================================   
    @PostMapping("/recommend")
    public String getRecommendation(@ModelAttribute FarmInfo farmInfo, Model model) {
        // Call AI service
        String recommendation = OpenAiService.getRecommendation(farmInfo);
        
        // Save recommendation in FarmInfo
        farmInfo.setRecommendation(recommendation);

        // Also set farmerId from session
        signupInfo farmer = (signupInfo) session.getAttribute("loggedInFarmer");
        farmInfo.setFarmerId(farmer.getId());

        // Save farmInfo including recommendation
        farmInfoService.saveFarm(farmInfo);

        // Pass recommendation to result page
        model.addAttribute("recommendation", recommendation);
        
        return "farmer/result";
    }


}
