	package com.project.sma.Controller;

	import com.project.sma.Model.signupInfo;
	
import com.project.sma.ServiceAPI.signupInfoService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.multipart.MultipartFile;
	import org.springframework.web.bind.annotation.*;
	import org.springframework.web.servlet.mvc.support.RedirectAttributes;
	
	import java.io.InputStream;
	import java.nio.file.Files;
	import java.nio.file.Path;
	import java.nio.file.Paths;
	import java.nio.file.StandardCopyOption;

	@Controller
	@RequestMapping("/farm")
	public class MainController {

		@Autowired
		private signupInfoService signupService;


	   
	    
//=================== Start Index page ================================= 
		@GetMapping("/")
		public String showIndex() {
		   
		    return "Index";  
		}

//================================== Index page End =========================================
	    
	   
// =================== =========== Sign-Up Start================= ===================
		@GetMapping("/FarmerSignup")
		public String showFarmerSignup(Model model) {
		    // Create a new signupInfo object for form binding
		    signupInfo farmer = new signupInfo();
		    
		    // Add it to the model so Thymeleaf form can bind fields
		    model.addAttribute("farmerInfo", farmer);
		    
		    // Return the Thymeleaf template name
		    return "FarmerSignup"; // agar tumne farmer folder me rakha hai
		}


	    //                        ======== POST Farmer Sign-Up ======
	    @PostMapping("/FarmerSignup")
	    public String saveFarmer(@ModelAttribute("farmerInfo") signupInfo farmerInfo,
	                             @RequestParam("profilePicFile") MultipartFile profilePic,
	                             RedirectAttributes attributes) {
	        try {
	            if (!profilePic.isEmpty()) {
	                String fileName = System.currentTimeMillis() + "_" + profilePic.getOriginalFilename();
	                String uploadDir = "public/ProfilePic/";
	                Path path = Paths.get(uploadDir);
	                if (!Files.exists(path)) Files.createDirectories(path);

	                try (InputStream inputStream = profilePic.getInputStream()) {
	                    Files.copy(inputStream, path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
	                }

	                farmerInfo.setProfilePic(fileName);
	            }

	            // ===============================Save to DB================
	            signupService.save(farmerInfo);

	            attributes.addFlashAttribute("msg", "✅ Farmer registered successfully!");
	        } catch (Exception e) {
	            attributes.addFlashAttribute("msg", "❌ Error: " + e.getMessage());
	        }

	        return "redirect:/farm/FarmerSignup";
	    }
	    
//====================================Sign up end++==========================	    
	    
	    
	    
//= ==================================Login Page  Start====================
	    @GetMapping("/FarmerLogin")
	    public String showFarmerLogin() {
	        return "FarmerLogin"; // Thymeleaf template
	    }

	    //              ===========  Handle Login POST ============
	    @PostMapping("/FarmerLogin")
	    public String farmerLogin(@RequestParam("email") String email,
	                              @RequestParam("password") String password,
	                              RedirectAttributes attributes,
	                              HttpSession session) {
	        try {
	            signupInfo farmer = signupService.findByEmail(email); // method in service

	            if (farmer == null) {
	                attributes.addFlashAttribute("msg", "❌ Farmer not found!");
	                return "redirect:/farm/FarmerLogin";
	            }

	            if (farmer.getEmail().equals(email) && farmer.getPassword().equals(password)) {
	                // =================Create session====================
	                session.setAttribute("loggedInFarmer", farmer);
	                attributes.addFlashAttribute("msg", "✅ Login successful!");
	                return "redirect:/farm/Dashboard"; // Create Dashboard page later
	            } else {
	                attributes.addFlashAttribute("msg", "❌ Invalid email or password!");
	                return "redirect:/farm/FarmerLogin";
	            }
	        } catch (Exception e) {
	            attributes.addFlashAttribute("msg", "❌ Error: " + e.getMessage());
	            return "redirect:/farm/FarmerLogin";
	        }
	    }

//=========================================Login page End=======================================
	    
	    
	    

	
	}
